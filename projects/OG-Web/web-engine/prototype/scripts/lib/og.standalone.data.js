(function ($, undefined) {
    var settings = {
        background_color: '#fff',
        max_col_width: 300 // the maximum default width for a column
    };
    $.fn.ogdata = function (input) {
        var $selector = $(this), grid_width = $selector.width(), util = {}, gadget = {}, grid, cols,
            data, json_strify = JSON.stringify, json_parse = JSON.parse;
        /**
         * @param d {Object} object with data & labels arrays
         * @param num
         */
        gadget.getCntr = function () {
            return $selector;
        };
        gadget.load = function (input) {
            $selector.css({
                'width': grid_width + 'px',
                'float': 'left',
                'height': $selector.height() + 'px',
                'backgroundColor': settings.background_color
            });
            var pdata = util.process_data(input);
            cols = json_strify(pdata.columns);
            data = json_strify(pdata.data);
            grid = new Slick.Grid($selector, pdata.data, pdata.columns);
        };
        gadget.update = function(input) {
            var pdata = util.process_data(input);
            if (data !== json_strify(pdata.data))
                data = json_strify(pdata.data), grid.setData(pdata.data), grid.invalidate();
            if (cols !== json_strify(pdata.columns))
                cols = json_strify(pdata.columns), grid.setColumns(pdata.columns), grid.invalidate();
        };
        gadget.on_column_resize = function(e, args) {
            var columns = args.grid.getColumns(), len = columns.length-1, i = 0, c = json_parse(cols), w;
            for (; i < len; i++) {
                if (columns[i].width !== c[i].width) c[i].width = columns[i].width;
            }
            cols = json_strify(c);
        };
        gadget.die = function () {
            if (grid !== undefined) grid.invalidate(), grid.destroy(), grid = undefined;
        };
        gadget.resize = function () {
            var parent;
            if (grid !== undefined && (parent = $selector.parent('.OG-gadget-container')) !== undefined) {
                $selector.width(parent.width()).height(parent.height());
                grid.resizeCanvas();
            }
        };
        /**
         * Create html columns to house the grids
         */
        util.process_data = function(object){
            var ismatrix, xlabels, ylabels, col_width, cols, obj_data;
            ismatrix = (object.data['matrix'] && !$.isEmptyObject(object.data['matrix']));
            if (!ismatrix && !object.labels) object.labels = ['Label', 'Value'];
            xlabels = ismatrix ? object.data['xLabels'] : object.labels;
            ylabels = ismatrix ? object.data['yLabels'] : null;
            col_width = util.column_width(xlabels, settings.max_col_width, ismatrix);
            cols = [];
            if (ismatrix){
                cols.push({id: 'ylabelscol', name: '', field: 'ylabelscol', width: col_width});
                $selector.addClass('matrix');
            }else{
                $selector.removeClass('matrix');
            }
            xlabels.forEach(function (val) {
                cols.push({id: val, name: val, field: val, width: col_width});
            });
            obj_data = ismatrix ? object.data['matrix'] : object.data;
            return { 
                data: obj_data.reverse().reduce(function (acc, val, i) {
                    var obj = {};
                    if (ismatrix) obj.ylabelscol = ylabels[i];
                    val.forEach(function (val, i) {obj[xlabels[i]] = val;});
                    return acc.push(obj) && acc;
                }, []),
                columns:cols
            };
        };
        /**
         * Calculate the default width of columns
         * @param data {Object} object with data & labels arrays
         * @param max {Number} maximum width to return
         * @param matrix {Boolean}
         * @return {Number}
         */
        util.column_width = function (cols, max, matrix) {
            var cols = cols.length + (matrix ? 1 : 0),
                potential_width = grid_width / cols,
                scrollbar_width = 18; // TODO: get shared scroll width
            return potential_width > max ? max : potential_width - (scrollbar_width / cols);
        };
        gadget.load(input);
        return gadget;
    };
})(jQuery);