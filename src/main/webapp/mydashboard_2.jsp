<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Storage Dashboard</title>
        <script src="resources/js/c3.min.js" type="text/javascript"></script>
        <script src="resources/js/d3.min.js" type="text/javascript"></script>
        <link href="resources/css/c3.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="">
    </head>
    <body>
        <div id="chart1">
            <h3 align="center" id="title1"></h3>
            <h3 align="center" id="succeeded1"></h3>
            <h3 align="center" id="failed1"></h3>
            <div id="capacities1"></div>
            <!--            <input id="btn_capacities1" class="back_cap" type="button" value="Back"></input><br />-->
        </div>
        <div id="chart2">
            <h3 align="center" id="title2"></h3>
            <h3 align="center" id="succeeded2"></h3>
            <h3 align="center" id="failed2"></h3>
            <div id="capacities2"></div>
            <!--<input id="btn_capacities2" class="back_cap" type="button" value="Back"></input>-->
        </div>
        <!--        <div id="chart3">
                    <h3 align="center" id="title3"></h3>
                    <div id="capacities3"></div>
        
                    <input id="btn_capacities3" class="back_cap" type="button" value="Back"></input>
                </div>        
                <div id="chart4">
                    <h3 align="center" id="title4"></h3>
                    <div id="capacities4"></div>
                    <input id="btn_capacities4" class="back_cap" type="button" value="Back"></input>
                </div>
                <div id="chart5" style="width: 850px; height: 500px" >
                    <h3 align="center" id="title5"></h3>
                    <div id="capacities5"></div>
                    <input id="btn_capacities5" class="back_cap" type="button" value="Back"></input>
                </div>-->
        <script>
            var succeeded = [];
            var failed = [];
            var aggregated_mv3lavun01prp = [];
            var aggregated_mv2lavun01prp = [];
            var top10 = [];
            function drawPieChart(chart_id, server, aggr, width, height) {
                var chart = c3.generate({
                    bindto: chart_id,
                    size: {
                        height: width,
                        width: height
                    },
                    data: {
                        columns: [
                            failed,
                            succeeded
                        ],
                        type: 'pie',
                    },
                    donut: {
                        title: server
                    },
                    legend: {
                        show: true,
                        position: 'inset',
                        inset: {
                            anchor: 'top-right',
                            x: undefined,
                            y: undefined,
                            step: undefined
                        }
                    },
                    color: {
                        pattern: ["red", "green"]
                    },
                    tooltip: {
                        format: {
                            value: function (value, ratio) {
                                var percentFormat = d3.format('.1%');
                                var numberFormat = d3.format('');
                                return percentFormat(ratio) + "(" + numberFormat(value) + ")";
                            }
                        }
                    },
                });
//                d3.select(chart_id).append("text")
//                        .attr("x", 100)
//                        .attr("y", 150)
//                        .attr("font-family", "sans-serif")
//                        .style("text-anchor", "middle")
//                        .text(server);
            }

            function drawBarChart(chart_id, aggr, title, y_label, width, height) {
                var chart = c3.generate({
                    bindto: chart_id,
                    size: {
                        height: height,
                        width: width
                    },
                    data: {
                        columns: aggr,
                        type: 'bar',
                    },
                    bar: {
                        width: {
                            ratio: 0.5 // this makes bar width 50% of length between ticks
                        }
                    },
                    axis: {
                        x: {
                            label: 'X Label'
                        },
                        y: {
                            label: y_label
                        },
                        y2: {
                            show: false,
                            label: 'Y2 Label'
                        }
                    },
                    tooltip: {
                        grouped: false,
                        format: {
                            title: function (d) {
                                return title;
                            },
                            value: function (value, ratio, id) {
//                                var format = id === 'data1' ? d3.format(',') : d3.format('$');
//                                return format(value);
                                return value;
                            }
                        }
                    }

                });
            }
            function processBarChart(chart_id, title, server, aggr, chart_title, y_label, width, height) {
                document.getElementById(title).innerHTML = server + " / " + today;
                drawBarChart(chart_id, aggr, chart_title, y_label, width, height);
            }

            function getSum(total, num) {
                return total + num;
            }
            function processPieChart(data, chart_id, title, width, height) {
                failed = [];
                succeeded = [];
                aggregated_mv3lavun01prp = ${controller.fetchAggrMv3lavun01prp()};
                aggregated_mv2lavun01prp = ${controller.fetchAggrMv2lavun01prp()};
                lastSevenDays = ${controller.lastSevenDays};
                server = data[0].server.split(".")[0];
                today = data[0].mydate;
                succeeded.push("Succeeded");
                failed.push("Failed");
                for (i = 0; i < data.length; i++) {
                    succeeded.push(data[i]["succeeded"]);
                }
                for (i = 0; i < data.length; i++) {
                    failed.push(data[i]["failed"]);
                }
                failed_shift = failed.slice();
                succeeded_shift = succeeded.slice();
                failed_shift.shift();
                succeeded_shift.shift();
//                document.getElementById(title).innerHTML = server + " / " + today;
                document.getElementById(title).innerHTML = server;
                if (data[0].server === "mv3lavun01prp.smrc.sidra.org") {
                    const failed_sum = failed_shift.reduce(getSum);
                    const succeeded_sum = succeeded_shift.reduce(getSum);
                    document.getElementById("failed1").innerHTML = failed_sum;
                    document.getElementById("succeeded1").innerHTML = succeeded_sum;
                    drawPieChart(chart_id, server, aggregated_mv3lavun01prp, width, height);
                } else {
                    const failed_sum = failed_shift.reduce(getSum);
                    const succeeded_sum = succeeded_shift.reduce(getSum);
                    document.getElementById("failed2").innerHTML = "Failed" + failed_sum;
                    document.getElementById("succeeded2").innerHTML = succeeded_sum;
                    drawPieChart(chart_id, server, aggregated_mv2lavun01prp, width, height);
                }
            }
            mv3lavun01prp = ${controller.fetchMv3lavun01prp()};
            mv2lavun01prp = ${controller.fetchMv2lavun01prp()};
            top10 = ${controller.fetchTopTen()};
            processPieChart(mv3lavun01prp, "#capacities1", "title1", 300, 300);
            processPieChart(mv2lavun01prp, "#capacities2", "title2", 500, 500);
//            processBarChart("#capacities3", "title3", "mv3lavun01prp es3", aggregated_mv3lavun01prp, "Failures per Group", "No. of Failures",
//                    300, 300);
//            processBarChart("#capacities4", "title4", "mv2lavun01prp number of failures", aggregated_mv2lavun01prp, "Failures per Group", "No. of Failures",
//                    300, 300);
//            processBarChart("#capacities5", "title5", "mv3lavun01prp es5", top10, "Size (GBs) of top 10", "Size(GBs)",
//                    600, 500);
            //            var dataList = dataString.split(",");
            ////            console.log(dataString);
            ////            function drawBarChart() {
            //            // this is the first chart
            //            var chart1 = c3.generate({
            //                bindto: "#capacities1",
            //                color: {
            //                    pattern: ['#1f77b4', '#aec7e8']
            //                },
            //                data: {
            //                    x: 'x',
            //                    columns: [
            //                        ['x', 'MV3 VMAX', 'MV2 VMAX'],
            //                        ['Total (GBs)', 244000, 244000],
            //                        ['Used (GBs)', 200000, 140000]
            //                    ],
            //                    type: 'bar',
            //                    order: "null",
            //                    labels: true,
            //                    onclick: function (d, element) {
            //                        old_data = d;
            //                        if (old_data.id === "Used (GBs)") {
            //                            chart1.load({
            //                                unload: ['VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO', 'Total (GBs)', 'Used (GBs)'],
            //                                columns: [
            //                                    ['x', 'VPLEX', 'Infra', 'AIX', 'OVM'],
            //                                    ['Utilization', 50, 50, 50, 50]
            //                                ]
            //                            });
            //                        }
            //                    }
            //                },
            //                axis: {
            //                    x: {
            //                        type: 'category' // this needed to load string x value
            //                    },
            //                    y: {
            //                        label: 'Capacity'
            //                    }
            //                }
            //            });
            //            d3.select('#btn_capacities1')
            //                    .on('click', function (d, element) {
            //                        chart1.load({
            //                            unload: true,
            //                            columns: [
            //                                ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
            //                                ['Total (GBs)', 90, 200, 160, 400, 10],
            //                                ['Used (GBs)', 30, 100, 140, 200, 6]
            //                            ]
            //                        });
            //                    });
            //            // this is the second chart
            //
            //            var chart2 = c3.generate({
            //                bindto: "#capacities2",
            //                color: {
            //                    pattern: ['#1f77b4', '#aec7e8']
            //                },
            //                data: {
            //                    x: 'x',
            //                    columns: [
            //                        ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
            //                        ['Total (GBs)', 90, 200, 160, 400, 10],
            //                        ['Used (GBs)', 30, 100, 140, 200, 6]
            //                    ],
            //                    type: 'bar',
            //                    order: "null",
            //                    labels: true,
            //                    onclick: function (d, element) {
            //                        old_data = d;
            //                        if (old_data.id === "Used (GBs)") {
            //                            chart2.load({
            //                                unload: ['VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO', 'Total (GBs)', 'Used (GBs)'],
            //                                columns: [
            //                                    ['x', 'VPLEX', 'Infra', 'AIX', 'OVM'],
            //                                    ['Utilization', 50, 50, 50, 50]
            //                                ]
            //                            });
            //                        }
            //                    }
            //                },
            //                axis: {
            //                    x: {
            //                        type: 'category' // this needed to load string x value
            //                    },
            //                    y: {
            //                        label: 'Capacity'
            //                    }
            //
            //                }
            //            });
            //            d3.select('#btn_capacities2')
            //                    .on('click', function (d, element) {
            //                        chart2.load({
            //                            unload: true,
            //                            columns: [
            //                                ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
            //                                ['Total (GBs)', 90, 200, 160, 400, 10],
            //                                ['Used (GBs)', 30, 100, 140, 200, 6]
            //                            ]
            //                        });
            //                    });
            //            // this is the third chart
            //
            //            var chart3 = c3.generate({
            //                bindto: "#capacities3",
            //                color: {
            //                    pattern: ['#1f77b4', '#aec7e8']
            //                },
            //                data: {
            //                    x: 'x',
            //                    columns: [
            //                        ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
            //                        ['Total (GBs)', 90, 200, 160, 400, 10],
            //                        ['Used (GBs)', 30, 100, 140, 200, 6]
            //                    ],
            //                    type: 'bar',
            //                    order: "null",
            //                    labels: true,
            //                    onclick: function (d, element) {
            //                        old_data = d;
            //                        if (old_data.id === "Used (GBs)") {
            //                            chart3.load({
            //                                unload: ['VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO', 'Total (GBs)', 'Used (GBs)'],
            //                                columns: [
            //                                    ['x', 'VPLEX', 'Infra', 'AIX', 'OVM'],
            //                                    ['Utilization', 50, 50, 50, 50]
            //                                ]
            //                            });
            //                        }
            //                    }
            //                },
            //                axis: {
            //                    x: {
            //                        type: 'category' // this needed to load string x value
            //                    },
            //                    y: {
            //                        label: 'Capacity'
            //                    }
            //
            //                }
            //            });
            //            d3.select('#btn_capacities3')
            //                    .on('click', function (d, element) {
            //                        chart3.load({
            //                            unload: true,
            //                            columns: [
            //                                ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
            //                                ['Total (GBs)', 90, 200, 160, 400, 10],
            //                                ['Used (GBs)', 30, 100, 140, 200, 6]
            //                            ]
            //                        });
            //                    });
            //            // this is the fourth chart
            //
            //            var chart4 = c3.generate({
            //                bindto: "#capacities4",
            //                color: {
            //                    pattern: ['#1f77b4', '#aec7e8']
            //                },
            //                data: {
            //                    x: 'x',
            //                    columns: [
            //                        ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
            //                        ['Total (GBs)', 90, 200, 160, 400, 10],
            //                        ['Used (GBs)', 30, 100, 140, 200, 6]
            //                    ],
            //                    type: 'bar',
            //                    order: "null",
            //                    labels: true,
            //                    onclick: function (d, element) {
            //                        old_data = d;
            //                        if (old_data.id === "Used (GBs)") {
            //                            chart4.load({
            //                                unload: ['VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO', 'Total (GBs)', 'Used (GBs)'],
            //                                columns: [
            //                                    ['x', 'VPLEX', 'Infra', 'AIX', 'OVM'],
            //                                    ['Utilization', 50, 50, 50, 50]
            //                                ]
            //                            });
            //                        }
            //                    }
            //                },
            //                axis: {
            //                    x: {
            //                        type: 'category' // this needed to load string x value
            //                    },
            //                    y: {
            //                        label: 'Capacity'
            //                    }
            //
            //                }
            //            });
            //            d3.select('#btn_capacities4')
            //                    .on('click', function (d, element) {
            //                        chart4.load({
            //                            unload: true,
            //                            columns: [
            //                                ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
            //                                ['Total (GBs)', 90, 200, 160, 400, 10],
            //                                ['Used (GBs)', 30, 100, 140, 200, 6]
            //                            ]
            //                        });
            //                    });
            // this is the fifth chart

            //            var chart5 = c3.generate({
            //            bindto: "#capacities5",
            //                    color: {
            //                    pattern: ['#1f77b4', '#aec7e8']
            //                    },
            //                    data: {
            //                    x: 'x',
            //                            columns: [
            //                            ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
            //                            ['Total (GBs)', 90, 200, 160, 400, 10],
            //                            ['Used (GBs)', 30, 100, 140, 200, 6]
            //                            ],
            //                            type: 'bar',
            //                            order: "null",
            //                            labels: true,
            //                            onclick: function (d, element) {
            //                            old_data = d;
            //                            if (old_data.id === "Used (GBs)") {
            //                            chart5.load({
            //                            unload: ['VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO', 'Total (GBs)', 'Used (GBs)'],
            //                                    columns: [
            //                                    ['x', 'VPLEX', 'Infra', 'AIX', 'OVM'],
            //                                    ['Utilization', 50, 50, 50, 50]
            //                                    ]
            //                            });
            //                            }
            //                            }
            //                    },
            //                    axis: {
            //                    x: {
            //                    type: 'category' // this needed to load string x value
            //                    },
            //                            y: {
            //                            label: 'Capacity'
            //                            }
            //
            //                    }
            //            });
            //            d3.select('#btn_capacities5')
            //                    .on('click', function (d, element) {
            //                    chart5.load({
            //                    unload: true,
            //                            columns: [
            //                            ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
            //                            ['Total (GBs)', 90, 200, 160, 400, 10],
            //                            ['Used (GBs)', 30, 100, 140, 200, 6]
            //                            ]
            //                    });
            //                    });
            //            }
            //
            //            drawBarChart();
        </script>
    </body>
</html>
