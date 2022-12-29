import 'package:bloodify_front_end/modules/institution/stats/chart_item.dart';
import 'package:bloodify_front_end/modules/institution/stats/stats_cubit.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:syncfusion_flutter_charts/charts.dart';


class Statistics extends StatefulWidget {
  const Statistics({Key? key}) : super(key: key);

  @override
  State<Statistics> createState() => _StatisticsState();
}

class _StatisticsState extends State<Statistics> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Institution Statistics"),),
      body: RefreshIndicator(
        onRefresh: () async { StatsCubit.get(context).loadStats(); },
        child: Container(
        padding: EdgeInsets.symmetric(vertical: 16),
          alignment: Alignment.center,
          child: BlocBuilder<StatsCubit, StatsState>(
            builder: (context, state){
              if(state is StatsLoading){
                return const Center(
                  child: CircularProgressIndicator(),
                );
              }
              else if(state is StatsLoaded){
                return Center(
                  child: ChartsWidget(state.requestedBlood, state.availableBlood, state.donationAndTransactionBlood),
                );
              }
              else if (state is StatsError){
                return Center(
                    child: ErrWidget(),
                );
              }
              else{
                return Center(
                  child: Container(),
                );
              }
            }
          )
    ),
      ),
    );
  }
}

Widget ChartsWidget(List<RequestedOrAvailableBlood> requestedBlood, List<RequestedOrAvailableBlood> availableBlood,
                    List<TransactionBlood> donationAndTransactionBlood){
  return SingleChildScrollView(
    child: Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Card(
            color: Colors.white70,
            child: SizedBox(
                height: 350,
                child: Center(
                  child: SfCircularChart(
                      title: ChartTitle(text: "Blood types in Related Posts",
                          textStyle: const TextStyle(
                              fontSize: 15,
                              fontWeight: FontWeight.bold
                          )
                      ),
                      legend: Legend(
                          isVisible: true,
                          textStyle: const TextStyle(
                              fontSize: 15,
                              fontWeight: FontWeight.bold
                          )
                      ),
                      series: <CircularSeries>[
                        // Render pie chart
                        DoughnutSeries<RequestedOrAvailableBlood, String>(
                          dataSource: requestedBlood,
                          xValueMapper: (RequestedOrAvailableBlood data, _) => data.type,
                          yValueMapper: (RequestedOrAvailableBlood data, _) => data.bags,
                          animationDelay: 1,
                          animationDuration: 1,
                          dataLabelSettings:const DataLabelSettings(
                              isVisible : true,
                              showZeroValue: true,
                              textStyle: TextStyle(
                                  fontSize: 15,
                                  fontWeight: FontWeight.bold
                              )
                          ),
                          name: "Data",
                          radius: '100%',
                          explode: true,
                        )
                      ]
                  ),
                )
            ),
          ),
          Card(
            color: Colors.white70,
            child: SizedBox(
                height: 500,
                child: Center(
                    child: SfCartesianChart(
                      primaryXAxis: CategoryAxis(
                          labelStyle: const TextStyle(
                              fontSize: 15,
                              fontWeight: FontWeight.bold
                          )
                      ),
                      // primaryYAxis: CategoryAxis(
                      //     labelStyle: const TextStyle(
                      //         fontSize: 11,
                      //         fontWeight: FontWeight.bold
                      //     )
                      // ),
                      title: ChartTitle(
                        text: "Blood type - Avaialable bags",
                        textStyle: const TextStyle(
                            fontSize: 15,
                            fontWeight: FontWeight.bold
                        ),
                      ),

                      series: <ChartSeries<RequestedOrAvailableBlood, String>>[
                        // Renders bar chart
                        BarSeries<RequestedOrAvailableBlood, String>(
                            dataSource: availableBlood,
                            xValueMapper: (RequestedOrAvailableBlood data, _) => data.type,
                            yValueMapper: (RequestedOrAvailableBlood data, _) => data.bags,
                            xAxisName: "Available Bags",
                            borderRadius: BorderRadius.circular(10),
                            color: Colors.red.shade700,
                            animationDelay: 1,
                            animationDuration: 1,
                            dataLabelSettings: const DataLabelSettings(
                                isVisible: true,
                                showZeroValue: true,
                                textStyle: TextStyle(
                                    fontSize: 15,
                                    fontWeight: FontWeight.bold
                                )
                            )
                        )
                      ],
                    )
                )
            ),
          ),
          Card(
            color: Colors.white70,
            child: SizedBox(
                height: 900,
                child: Center(
                    child: SfCartesianChart(
                        primaryXAxis: CategoryAxis(
                            labelStyle: const TextStyle(
                                fontSize: 15,
                                fontWeight: FontWeight.bold
                            )
                        ),
                        title: ChartTitle(
                          text: "Last Month Transactions",
                          textStyle: const TextStyle(
                              fontSize: 15,
                              fontWeight: FontWeight.bold
                          ),
                        ),
                        legend: Legend(
                          isVisible: true,
                          textStyle: const TextStyle(
                              fontSize: 15,
                              fontWeight: FontWeight.bold
                          ),
                        ),
                        series: <ChartSeries<TransactionBlood, String>>[
                          StackedBarSeries<TransactionBlood, String>(
                            color: Colors.red.shade900,
                            groupName: "Transactions",
                            name: "related Transactions",
                            dataSource: donationAndTransactionBlood,
                            xValueMapper: (TransactionBlood data, _) => data.type,
                            yValueMapper: (TransactionBlood data, _) => data.bagsTransacted,
                            dataLabelSettings: const DataLabelSettings(
                              isVisible: true,
                              showCumulativeValues: true,
                              textStyle: TextStyle(
                                fontWeight: FontWeight.bold,
                                fontSize: 15,
                              ),
                            ),
                          ),
                          StackedBarSeries<TransactionBlood, String>(
                              color: Colors.red.shade100,
                              groupName: "Donations",
                              name: "Donations",
                              dataSource: donationAndTransactionBlood,
                              xValueMapper: (TransactionBlood data, _) => data.type,
                              yValueMapper: (TransactionBlood data, _) => data.bagsDonated,
                              dataLabelSettings: const DataLabelSettings(
                                isVisible: true,
                                showCumulativeValues: true,
                                textStyle: TextStyle(
                                    fontWeight: FontWeight.bold,
                                    fontSize: 15
                                ),
                              )
                          )
                        ]
                    )
                )
            ),
          ),
        ],
      ),
    ),
  );
}

Widget ErrWidget(){
  return const Center(
    child: Text("something went wrong"),
  );
}

