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
    </head>
    <body>

        <div id="chart1">
            <div id="capacities1"></div>
            <input id="btn_capacities1" class="back_cap" type="button" value="Back"></input>
        </div>
        <div id="chart2">
            <div id="capacities2"></div>
            <input id="btn_capacities2" class="back_cap" type="button" value="Back"></input>
        </div>
        <div id="chart3">
            <div id="capacities3"></div>
            <input id="btn_capacities3" class="back_cap" type="button" value="Back"></input>
        </div>
        <div>
            <h3 align="center" id="title4"></h3>
            <div id="chart4">
                <div id="capacities4"></div>
                <input id="btn_capacities4" class="back_cap" type="button" value="Back"></input>
                <br />
                <table border="1" align="center" style="background-color: white">
                    <th style="color: black; background-color: whitesmoke">Succeeded</th>
                    <th style="color: orangered; background-color: whitesmoke">Failed</th>
                    <tr>
                        <td id="succeeded_id4"></td>
                        <td id="failed_id4"></td>
                    </tr>
                </table>
            </div>
        </div>
        <div>
            <h3 align="center" id="title5"></h3>
            <div id="chart5">
                <div id="capacities5"></div>
                <input id="btn_capacities5" class="back_cap" type="button" value="Back"></input>
                <br />
                <table border="1" align="center" style="background-color: white">
                    <th style="color: black; background-color: whitesmoke">Succeeded</th>
                    <th style="color: orangered; background-color: whitesmoke">Failed</th>
                    <tr>
                        <td id="succeeded_id5"></td>
                        <td id="failed_id5"></td>
                    </tr>
                </table>
            </div>
        </div>
        <div>
            <h3 align="center" id="title6"></h3>
            <div id="chart6">
                <div id="capacities6"></div>
                <input id="btn_capacities6" class="back_cap" type="button" value="Back"></input>
                <br />
                <table border="1" align="center" style="background-color: white">
                    <th style="color: black; background-color: whitesmoke">Succeeded</th>
                    <th style="color: orangered; background-color: whitesmoke">Failed</th>
                    <tr>
                        <td id="succeeded_id6"></td>
                        <td id="failed_id6"></td>
                    </tr>
                </table>
            </div>
        </div>
        <script>
            var succeeded = [];
            var failed = [];
            function pieChart(chart_id) {
                // this is the sixth chart
                var chart = c3.generate({
                    bindto: chart_id,
                    data: {
                        // iris data from R
                        columns: [
                            succeeded,
                            failed,
                        ],
                        type: 'pie',
                    }
                });
            }

            function getSum(total, num) {
                return total + num;
            }
            var old_data;
            var testvar1 = [];
            data1 = Math.floor(Math.random() * 60) + 1;
            data2 = 60 - data1;
            var dataString = "${controller.findAll()}".trim();
            function processBackup(data, chart_id, title, succeeded_id, failed_id) {
                failed = [];
                succeeded = [];
                server = data[0].server;
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
                document.getElementById(failed_id).innerHTML = failed_shift.reduce(getSum, 0);
                document.getElementById(succeeded_id).innerHTML = succeeded_shift.reduce(getSum, 0);
                document.getElementById(title).innerHTML = server;
                pieChart(chart_id);
            }
            mv3lavun01prp = ${controller.fetchMv3lavun01prp()};
            mv2lavun01prp = ${controller.fetchMv2lavun01prp()};
            processBackup(mv3lavun01prp, "#capacities5", "title5", "succeeded_id5", "failed_id5");
            processBackup(mv2lavun01prp, "#capacities6", "title6", "succeeded_id6", "failed_id6");
            var dataList = dataString.split(",");
//            console.log(dataString);
            function drawBarChart() {
                // this is the first chart
                var chart1 = c3.generate({
                    bindto: "#capacities1",
                    color: {
                        pattern: ['#1f77b4', '#aec7e8']
                    },
                    data: {
                        x: 'x',
                        columns: [
                            ['x', 'MV3 VMAX', 'MV2 VMAX'],
                            ['Total (GBs)', 244000, 244000],
                            ['Used (GBs)', 200000, 140000]
                        ],
                        type: 'bar',
                        order: "null",
                        labels: true,
                        onclick: function (d, element) {
                            old_data = d;
                            if (old_data.id === "Used (GBs)") {
                                chart1.load({
                                    unload: ['VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO', 'Total (GBs)', 'Used (GBs)'],
                                    columns: [
                                        ['x', 'VPLEX', 'Infra', 'AIX', 'OVM'],
                                        ['Utilization', 50, 50, 50, 50]
                                    ]
                                });
                            }
                        }
                    },
                    axis: {
                        x: {
                            type: 'category' // this needed to load string x value
                        },
                        y: {
                            label: 'Capacity'
                        }
                    }
                });
                d3.select('#btn_capacities1')
                        .on('click', function (d, element) {
                            chart1.load({
                                unload: true,
                                columns: [
                                    ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
                                    ['Total (GBs)', 90, 200, 160, 400, 10],
                                    ['Used (GBs)', 30, 100, 140, 200, 6]
                                ]
                            });
                        });
                // this is the second chart

                var chart2 = c3.generate({
                    bindto: "#capacities2",
                    color: {
                        pattern: ['#1f77b4', '#aec7e8']
                    },
                    data: {
                        x: 'x',
                        columns: [
                            ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
                            ['Total (GBs)', 90, 200, 160, 400, 10],
                            ['Used (GBs)', 30, 100, 140, 200, 6]
                        ],
                        type: 'bar',
                        order: "null",
                        labels: true,
                        onclick: function (d, element) {
                            old_data = d;
                            if (old_data.id === "Used (GBs)") {
                                chart2.load({
                                    unload: ['VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO', 'Total (GBs)', 'Used (GBs)'],
                                    columns: [
                                        ['x', 'VPLEX', 'Infra', 'AIX', 'OVM'],
                                        ['Utilization', 50, 50, 50, 50]
                                    ]
                                });
                            }
                        }
                    },
                    axis: {
                        x: {
                            type: 'category' // this needed to load string x value
                        },
                        y: {
                            label: 'Capacity'
                        }

                    }
                });
                d3.select('#btn_capacities2')
                        .on('click', function (d, element) {
                            chart2.load({
                                unload: true,
                                columns: [
                                    ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
                                    ['Total (GBs)', 90, 200, 160, 400, 10],
                                    ['Used (GBs)', 30, 100, 140, 200, 6]
                                ]
                            });
                        });
                // this is the third chart

                var chart3 = c3.generate({
                    bindto: "#capacities3",
                    color: {
                        pattern: ['#1f77b4', '#aec7e8']
                    },
                    data: {
                        x: 'x',
                        columns: [
                            ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
                            ['Total (GBs)', 90, 200, 160, 400, 10],
                            ['Used (GBs)', 30, 100, 140, 200, 6]
                        ],
                        type: 'bar',
                        order: "null",
                        labels: true,
                        onclick: function (d, element) {
                            old_data = d;
                            if (old_data.id === "Used (GBs)") {
                                chart3.load({
                                    unload: ['VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO', 'Total (GBs)', 'Used (GBs)'],
                                    columns: [
                                        ['x', 'VPLEX', 'Infra', 'AIX', 'OVM'],
                                        ['Utilization', 50, 50, 50, 50]
                                    ]
                                });
                            }
                        }
                    },
                    axis: {
                        x: {
                            type: 'category' // this needed to load string x value
                        },
                        y: {
                            label: 'Capacity'
                        }

                    }
                });
                d3.select('#btn_capacities3')
                        .on('click', function (d, element) {
                            chart3.load({
                                unload: true,
                                columns: [
                                    ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
                                    ['Total (GBs)', 90, 200, 160, 400, 10],
                                    ['Used (GBs)', 30, 100, 140, 200, 6]
                                ]
                            });
                        });
                // this is the fourth chart

                var chart4 = c3.generate({
                    bindto: "#capacities4",
                    color: {
                        pattern: ['#1f77b4', '#aec7e8']
                    },
                    data: {
                        x: 'x',
                        columns: [
                            ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
                            ['Total (GBs)', 90, 200, 160, 400, 10],
                            ['Used (GBs)', 30, 100, 140, 200, 6]
                        ],
                        type: 'bar',
                        order: "null",
                        labels: true,
                        onclick: function (d, element) {
                            old_data = d;
                            if (old_data.id === "Used (GBs)") {
                                chart4.load({
                                    unload: ['VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO', 'Total (GBs)', 'Used (GBs)'],
                                    columns: [
                                        ['x', 'VPLEX', 'Infra', 'AIX', 'OVM'],
                                        ['Utilization', 50, 50, 50, 50]
                                    ]
                                });
                            }
                        }
                    },
                    axis: {
                        x: {
                            type: 'category' // this needed to load string x value
                        },
                        y: {
                            label: 'Capacity'
                        }

                    }
                });
                d3.select('#btn_capacities4')
                        .on('click', function (d, element) {
                            chart4.load({
                                unload: true,
                                columns: [
                                    ['x', 'VMAX-3', 'Isilon', 'Unity', 'SMRC Unity', 'XtremIO'],
                                    ['Total (GBs)', 90, 200, 160, 400, 10],
                                    ['Used (GBs)', 30, 100, 140, 200, 6]
                                ]
                            });
                        });
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
            }

            drawBarChart();
        </script>
    </body>
</html>
