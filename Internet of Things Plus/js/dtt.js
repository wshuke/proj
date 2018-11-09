Highcharts.setOptions({
    global: {
        useUTC: false
    }
});

function activeLastPointToolip(chart) { //这个chart不能改
    var points = chart.series[0].points;
    chart.tooltip.refresh(points[points.length - 1]);
}
var chart = Highcharts.chart('wendu', { //.chart不能改
    chart: {
        type: 'spline',
        marginRight: 10,
        events: {
            load: function() {
                var series = this.series[0],
                    chart = this;
                activeLastPointToolip(chart);
                setInterval(function() {
                    var x = (new Date()).getTime(), // 当前时间
                        y = Math.random(); // 随机值
                    series.addPoint([x, y], true, true);
                    activeLastPointToolip(chart);
                }, 1000);
            }
        }
    },
    title: {
        text: '温度',
        style: {
            fontSize: '22px',
            fontWeight: 'bolder',
            color: '#990000'
        }
    },
    xAxis: {
        type: 'datetime',
        tickPixelInterval: 150
    },
    yAxis: {
        title: {
            text: null
        }
    },
    tooltip: {
        formatter: function() {
            return '<b>' + this.series.name + '</b><br/>' +
                Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                Highcharts.numberFormat(this.y, 2);
        }
    },
    legend: {
        enabled: false
    },
    series: [{
        name: '温度',
        data: (function() {
            // 生成随机值
            var data = [],
                time = (new Date()).getTime(),
                i;
            for (i = -19; i <= 0; i += 1) {
                data.push({
                    x: time + i * 1000,
                    y: Math.random()
                });
            }
            return data;
        }())
    }]
});



Highcharts.setOptions({
    global: {
        useUTC: false
    }
});

function activeLastPointToolip(chart) {
    var points = chart.series[0].points;
    chart.tooltip.refresh(points[points.length - 1]);
}
var chart = Highcharts.chart('gonglv', {
    chart: {
        type: 'spline',
        marginRight: 10,
        events: {
            load: function() {
                var series = this.series[0],
                    chart = this;
                activeLastPointToolip(chart);
                setInterval(function() {
                    var x = (new Date()).getTime(), // 当前时间
                        y = Math.random(); // 随机值
                    series.addPoint([x, y], true, true);
                    activeLastPointToolip(chart);
                }, 1000);
            }
        }
    },
    title: {
        text: '功率',
        style: {
            fontSize: '22px',
            fontWeight: 'bolder',
            color: '#003399'
        }
    },
    xAxis: {
        type: 'datetime',
        tickPixelInterval: 150
    },
    yAxis: {
        title: {
            text: null
        }
    },
    tooltip: {
        formatter: function() {
            return '<b>' + this.series.name + '</b><br/>' +
                Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                Highcharts.numberFormat(this.y, 2);
        }
    },
    legend: {
        enabled: false
    },
    series: [{
        name: '功率',
        data: (function() {
            // 生成随机值
            var data = [],
                time = (new Date()).getTime(),
                i;
            for (i = -19; i <= 0; i += 1) {
                data.push({
                    x: time + i * 1000,
                    y: Math.random()
                });
            }
            return data;
        }())
    }]
});


Highcharts.setOptions({
    global: {
        useUTC: false
    }
});

function activeLastPointToolip(chart) {
    var points = chart.series[0].points;
    chart.tooltip.refresh(points[points.length - 1]);
}
var chart = Highcharts.chart('dianya', {
    chart: {
        type: 'spline',
        marginRight: 10,
        events: {
            load: function() {
                var series = this.series[0],
                    chart = this;
                activeLastPointToolip(chart);
                setInterval(function() {
                    var x = (new Date()).getTime(), // 当前时间
                        y = Math.random(); // 随机值
                    series.addPoint([x, y], true, true);
                    activeLastPointToolip(chart);
                }, 1000);
            }
        }
    },
    title: {
        text: '电压',
        style: {
            fontSize: '22px',
            fontWeight: 'bolder',
            color: '#ff9900'
        }
    },
    xAxis: {
        type: 'datetime',
        tickPixelInterval: 150
    },
    yAxis: {
        title: {
            text: null
        }
    },
    tooltip: {
        formatter: function() {
            return '<b>' + this.series.name + '</b><br/>' +
                Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                Highcharts.numberFormat(this.y, 2);
        }
    },
    legend: {
        enabled: false
    },
    series: [{
        name: '电压',
        data: (function() {
            // 生成随机值
            var data = [],
                time = (new Date()).getTime(),
                i;
            for (i = -19; i <= 0; i += 1) {
                data.push({
                    x: time + i * 1000,
                    y: Math.random()
                });
            }
            return data;
        }())
    }]
});