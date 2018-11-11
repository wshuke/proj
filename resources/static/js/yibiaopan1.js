// 公共配置
    Highcharts.setOptions({
        chart: {
            type: 'solidgauge'
        },
        title: null,
        pane: {
            center: ['26%', '62%'], //第一个越小越靠左，第二个越小越靠上
            size: '80%',
            startAngle: -120,
            endAngle: 120,
            background: {
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
                innerRadius: '60%',
                outerRadius: '100%',
                shape: 'arc'
            }
        },
        tooltip: {
            enabled: false
        },
        yAxis: {
            stops: [
                [0.1, '#226DDD'], // green
                [0.5, '#DDDF0D'], // yellow
                [0.9, '#DF5353'] // red
            ],
            lineWidth: 3, //外边的阴影
            minorTickInterval: 'auto', //表盘加刻度线
            tickPixelInterval: 50, //表盘刻度数字间隔
            tickWidth: 0,
            title: {
                y: -55 //标题的高度，值越小越靠上
            },
            labels: {
                x: 0,
                y: -2, //分刻度线的高度，值越小越靠上
                style: { //刻度线的属性：颜色，大小，粗细，字体
                    color: '#000',
                    fontSize: '11px',
                    fontWeight: 'bolder',
                    fontFamily: '微软雅黑'
                }
            }
        },
        plotOptions: {
            solidgauge: {
                dataLabels: {
                    y: 50, //实时数据的高度，值越小越靠上
                    borderWidth: 0,
                    useHTML: true
                }
            }
        },
        credits: {
            enabled: false
        },
    });
    // A相电压表
    var dybA = Highcharts.chart('dybA', {
        yAxis: {
            min: 0,
            max: 400,
            title: { //标题
                text: 'A相电压',
                style: {
                    fontSize: '20px',
                    fontWeight: 'bolder',
                    color: '#003399'
                }
            }
        },
        credits: { //不加版权信息
            enabled: false
        },
        series: [{
            name: '电压',
            data: [380],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                    '<span style="font-size:20px;color:black">V</span></div>'
            },
            tooltip: {
                valueSuffix: ' V'
            }
        }]
    });
    // B相电压表
    var dybB = Highcharts.chart('dybB', {
        yAxis: {
            min: 0,
            max: 400,
            title: { //标题
                text: 'B相电压',
                style: {
                    fontSize: '20px',
                    fontWeight: 'bolder',
                    color: '#003399'
                }
            }
        },
        credits: { //不加版权信息
            enabled: false
        },
        series: [{
            name: '电压',
            data: [380],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                    '<span style="font-size:20px;color:black">V</span></div>'
            },
            tooltip: {
                valueSuffix: ' V'
            }
        }]
    });
    // C相电压表
    var dybC = Highcharts.chart('dybC', {
        yAxis: {
            min: 0,
            max: 400,
            title: { //标题
                text: 'C相电压',
                style: {
                    fontSize: '20px',
                    fontWeight: 'bolder',
                    color: '#003399'
                }
            }
        },
        credits: { //不加版权信息
            enabled: false
        },
        series: [{
            name: '电压',
            data: [380],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                    '<span style="font-size:20px;color:black">V</span></div>'
            },
            tooltip: {
                valueSuffix: ' V'
            }
        }]
    });
    // A相功率表
    var glA = Highcharts.chart('glA', {
        yAxis: {
            min: 0,
            max: 400,
            title: { //标题
                text: 'A相功率',
                style: {
                    fontSize: '20px',
                    fontWeight: 'bolder',
                    color: '#339900'
                }
            }
        },
        credits: { //不加版权信息
            enabled: false
        },
        series: [{
            name: '功率',
            data: [380],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                    '<span style="font-size:20px;color:black">KW</span></div>'
            },
            tooltip: {
                valueSuffix: ' KW'
            }
        }]
    });
    // B相功率表
    var glB = Highcharts.chart('glB', {
        yAxis: {
            min: 0,
            max: 400,
            title: { //标题
                text: 'B相功率',
                style: {
                    fontSize: '20px',
                    fontWeight: 'bolder',
                    color: '#339900'
                }
            }
        },
        credits: { //不加版权信息
            enabled: false
        },
        series: [{
            name: '功率',
            data: [380],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                    '<span style="font-size:20px;color:black">KW</span></div>'
            },
            tooltip: {
                valueSuffix: ' KW'
            }
        }]
    });
    // C相功率表
    var glC = Highcharts.chart('glC', {
        yAxis: {
            min: 0,
            max: 400,
            title: { //标题
                text: 'C相功率',
                style: {
                    fontSize: '20px',
                    fontWeight: 'bolder',
                    color: '#339900'
                }
            }
        },
        credits: { //不加版权信息
            enabled: false
        },
        series: [{
            name: '功率',
            data: [380],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                    '<span style="font-size:20px;color:black">KW</span></div>'
            },
            tooltip: {
                valueSuffix: ' KW'
            }
        }]
    });
    // 漏电流
    var ldl = Highcharts.chart('ldl', {
        yAxis: {
            min: 0,
            max: 400,
            title: { //标题
                text: '漏电流',
                style: {
                    fontSize: '20px',
                    fontWeight: 'bolder',
                    color: '#990000'
                }
            }
        },
        credits: { //不加版权信息
            enabled: false
        },
        series: [{
            name: '漏电流',
            data: [380],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                    '<span style="font-size:20px;color:black">A</span></div>'
            },
            tooltip: {
                valueSuffix: ' A'
            }
        }]
    });
    // 油温
    var yw = Highcharts.chart('yw', {
        yAxis: {
            min: 0,
            max: 400,
            title: { //标题
                text: '油温',
                style: {
                    fontSize: '20px',
                    fontWeight: 'bolder',
                    color: '#990000'
                }
            }
        },
        credits: { //不加版权信息
            enabled: false
        },
        series: [{
            name: '油温',
            data: [380],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                    '<span style="font-size:20px;color:black">℃</span></div>'
            },
            tooltip: {
                valueSuffix: ' '
            }
        }]
    });
    // 畸变率
    var jbl = Highcharts.chart('jbl', {
        yAxis: {
            min: 0,
            max: 400,
            title: { //标题
                text: '畸变率',
                style: {
                    fontSize: '20px',
                    fontWeight: 'bolder',
                    color: '#990000'
                }
            }
        },
        credits: { //不加版权信息
            enabled: false
        },
        series: [{
            name: '畸变率',
            data: [380],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                    '<span style="font-size:20px;color:black">KW</span></div>'
            },
            tooltip: {
                valueSuffix: ' KW'
            }
        }]
    });
    // 定时刷新数据
    setInterval(function() {
        var point,
            newVal,
            inc;
        if (dybA) {
            point = dybA.series[0].points[0];
            inc = Math.round((Math.random() - 0.5) * 100);
            newVal = point.y + inc;
            if (newVal < 0 || newVal > 400) {
                newVal = point.y - inc;
            }
            point.update(newVal);
        }
        if (dybB) {
            point = dybB.series[0].points[0];
            inc = Math.round((Math.random() - 0.5) * 100);
            newVal = point.y + inc;
            if (newVal < 0 || newVal > 400) {
                newVal = point.y - inc;
            }
            point.update(newVal);
        }
        if (dybC) {
            point = dybC.series[0].points[0];
            inc = Math.round((Math.random() - 0.5) * 100);
            newVal = point.y + inc;
            if (newVal < 0 || newVal > 400) {
                newVal = point.y - inc;
            }
            point.update(newVal);
        }
        if (glA) {
            point = glA.series[0].points[0];
            inc = Math.round((Math.random() - 0.5) * 100);
            newVal = point.y + inc;
            if (newVal < 0 || newVal > 400) {
                newVal = point.y - inc;
            }
            point.update(newVal);
        }
        if (glB) {
            point = glB.series[0].points[0];
            inc = Math.round((Math.random() - 0.5) * 100);
            newVal = point.y + inc;
            if (newVal < 0 || newVal > 400) {
                newVal = point.y - inc;
            }
            point.update(newVal);
        }
        if (glC) {
            point = glC.series[0].points[0];
            inc = Math.round((Math.random() - 0.5) * 100);
            newVal = point.y + inc;
            if (newVal < 0 || newVal > 400) {
                newVal = point.y - inc;
            }
            point.update(newVal);
        }
        if (ldl) {
            point = ldl.series[0].points[0];
            inc = Math.round((Math.random() - 0.5) * 100);
            newVal = point.y + inc;
            if (newVal < 0 || newVal > 400) {
                newVal = point.y - inc;
            }
            point.update(newVal);
        }
        if (yw) {
            point = yw.series[0].points[0];
            inc = Math.round((Math.random() - 0.5) * 100);
            newVal = point.y + inc;
            if (newVal < 0 || newVal > 400) {
                newVal = point.y - inc;
            }
            point.update(newVal);
        }
        if (jbl) {
            point = jbl.series[0].points[0];
            inc = Math.round((Math.random() - 0.5) * 100);
            newVal = point.y + inc;
            if (newVal < 0 || newVal > 400) {
                newVal = point.y - inc;
            }
            point.update(newVal);
        }
    }, 2000);