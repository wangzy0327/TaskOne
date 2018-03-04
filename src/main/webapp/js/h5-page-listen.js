/**
 * 监听H5翻页
 *
 */

var prevIndex = 0, // 上一页
    activeIndex = 0, // 当前页
    startTime = new Date().getTime(),
    pageInfo = [],
    isInitArr = true;

(function () {
    if (activeIndex == 0 && isInitArr && parent.module) {
        initLabel();
    }
})();

document.addEventListener('changedTo', function (e) {
    var indexStr = document.getElementsByClassName('page current')[0].id;

    prevIndex = activeIndex; // 上一页
    activeIndex = +indexStr.split('-')[1]; // 当前页

    /**
     * 监听翻页页面标签显示
     *
     */
    initLabel();

    /**
     * 监听阅读时间
     *
     */

    // 初始化数组
    if (isInitArr) {
        var len = document.getElementsByClassName('page').length;
        for (var i = 0; i < len; i++) {
            pageInfo[i] = 0;
        }
        isInitArr = false;
    }

    // 上一页阅读时间计算
    var endTime = new Date().getTime();
    var second = Math.round((endTime - startTime) / 1000); // 秒
    // 翻页时长少于1秒也算1秒
    second = second <= 0 ? 1 : second;

    if (pageInfo[prevIndex]) {
        pageInfo[prevIndex] = pageInfo[prevIndex] + second;
    } else {
        pageInfo[prevIndex] = second;
    }
    startTime = new Date().getTime();

});

function initLabel() {
    if (parent.module) {
        var labelNames = parent.module.data.labelNames;

        // 加载当前页的标签数据
        if (labelNames && labelNames.length) {
            var label_view_ele = parent.document.getElementById('label-view').children[0],
                html = '',
                activeLabelArr = labelNames[activeIndex];
            for (var i in activeLabelArr) {
                // todo html生成格式应该与createPageLabel 一样
                var activeLabe = activeLabelArr[i];
                html += '<div class="box-label label-close" data-i="' + i + '">';
                html += '<span data-id="' + activeLabe.id + '">' + activeLabe.name + '</span>';
                html += '<span class="label-hover label-hover-bg"></span>';
                html += '<span class="label-hover"><i class="iconfont icon-close">&#xe601;</i></span>';
                html += '</div>';
            }
            label_view_ele.innerHTML = html;
        }
    }
}