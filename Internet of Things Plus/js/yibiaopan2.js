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
    // 电流
    var guoliu = Highcharts.chart('guoliu', {
        yAxis: {
            min: 0,
            max: 400,
            title: { //标题
                text: '电流',
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
            name: '电流',
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
    // 电压
    var guoya = Highcharts.chart('guoya', {
        yAxis: {
            min: 0,
            max: 400,
            title: { //标题
                text: '电压',
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
    // 漏电流
    var loudianliu = Highcharts.chart('loudianliu', {
        yAxis: {
            min: 0,
            max: 400,
            title: { //标题
                text: '漏电流',
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
    var wendu = Highcharts.chart('wendu', {
        yAxis: {
            min: 0,
            max: 400,
            title: { //标题
                text: '油温',
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
    });// 定时刷新数据
    setInterval(function() {
        var point,
            newVal,
            inc;
        if (guoliu) {
            point = guoliu.series[0].points[0];
            inc = Math.round((Math.random() - 0.5) * 100);
            newVal = point.y + inc;
            if (newVal < 0 || newVal > 400) {
                newVal = point.y - inc;
            }
            point.update(newVal);
        }
        if (guoya) {
            point = guoya.series[0].points[0];
            inc = Math.round((Math.random() - 0.5) * 100);
            newVal = point.y + inc;
            if (newVal < 0 || newVal > 400) {
                newVal = point.y - inc;
            }
            point.update(newVal);
        }
        if (loudianliu) {
            point = loudianliu.series[0].points[0];
            inc = Math.round((Math.random() - 0.5) * 100);
            newVal = point.y + inc;
            if (newVal < 0 || newVal > 400) {
                newVal = point.y - inc;
            }
            point.update(newVal);
        }
        if (wendu) {
            point = wendu.series[0].points[0];
            inc = Math.round((Math.random() - 0.5) * 100);
            newVal = point.y + inc;
            if (newVal < 0 || newVal > 400) {
                newVal = point.y - inc;
            }
            point.update(newVal);
        }
            }, 2000);
