/**
 * 
 */
// 数字效果
$.fn.countTo = function (options) {
    options = options || {};

    return $(this).each(function () {
        // 设置当前元素的选项
        var settings = $.extend({}, $.fn.countTo.defaults, {
            from:            $(this).data('from'),
            to:              $(this).data('to'),
            speed:           $(this).data('speed'),
            refreshInterval: $(this).data('refresh-interval'),
            decimals:        $(this).data('decimals')
        }, options);

        // 多少次更新的值，以及每个更新的值增加多少
        var loops = Math.ceil(settings.speed / settings.refreshInterval),
            increment = (settings.to - settings.from) / loops;

        // references & variables that will change with each update
        var self = this,
            $self = $(this),
            loopCount = 0,
            value = settings.from,
            data = $self.data('countTo') || {};

        $self.data('countTo', data);

        // 如果可以找到现有的间隔，请先清除
        if (data.interval) {
            clearInterval(data.interval);
        }
        data.interval = setInterval(updateTimer, settings.refreshInterval);

        // 用起始值初始化元素
        render(value);

        function updateTimer() {
            value += increment;
            loopCount++;

            render(value);

            if (typeof(settings.onUpdate) == 'function') {
                settings.onUpdate.call(self, value);
            }

            if (loopCount >= loops) {
                // 删除间隔
                $self.removeData('countTo');
                clearInterval(data.interval);
                value = settings.to;

                if (typeof(settings.onComplete) == 'function') {
                    settings.onComplete.call(self, value);
                }
            }
        }

        function render(value) {
            var formattedValue = settings.formatter.call(self, value, settings);
            $self.html(formattedValue);
        }
    });
};

$.fn.countTo.defaults = {
    from: 0,               // 元素开始的数字
    to: 0,                 // 元素结束的数字
    speed: 1,           // 在目标号码之间计算多长时间
    refreshInterval: 0.5,  //  更新元素的频率
    decimals: 0,           // 要显示的小数位数
    formatter: formatter,  // 处理程序用于格式化渲染前的值
    onUpdate: null,        // 每次元素更新时的回调方法
    onComplete: null       // 元素完成更新时的回调方法
};

function formatter(value, settings) {
    return value.toFixed(settings.decimals);
}



// 自定义格式化示例
$('#count-number').data('', {
    formatter: function (value, options) {
        return value.toFixed(options.decimals).replace(/\B(?=(?:\d{3})+(?!\d))/g, ' ');
    }
});



/*$(window).scroll(function() {
    if ($(window).scrollTop() > 200) {//500 根据数字上面的内容 计算高度  滚到此区域 数字开始滚动
        //开始所有的计时器
        $('.timer').each(count);
        $('.count-title').removeClass('timer');
    }
});*/
$(function() {
	$('.timer').each(count);
    $('.count-title').removeClass('timer');
 });



function count(options) {
    var $this = $(this);
    options = $.extend({}, options || {}, $this.data('countToOptions') || {});
    $this.countTo(options);
}

