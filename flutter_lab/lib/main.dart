import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:flutter_todo_list/providers/todolist.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(
    ChangeNotifierProvider(create: (_) => Todolist(), child: MyApp()),
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Flutter Demo',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: MyHomePage(title: 'To do list'));
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, required this.title, this.text}) : super(key: key);

  final String title;
  final String? text;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  TextEditingController _contentEditController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title, style: TextStyle(color: Colors.white)),
      ),
      body: Padding(
        padding: const EdgeInsets.all(10),
        child: MyDataTable(),
      ),
      // Padding(
      //   padding: const EdgeInsets.all(10),
      //   child: MyDataTable(),
      // ),

      floatingActionButton: FloatingActionButton(
        onPressed: () => {
          showDialog<String>(
            context: context,
            builder: (BuildContext context) => AlertDialog(
              title: const Text('ToDoList 추가하기'),
              content: TextField(
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                ),
                controller: _contentEditController,
              ),
              actions: <Widget>[
                TextButton(
                  onPressed: () {
                    Navigator.pop(context, 'Cancel');
                    _contentEditController.clear();
                  },
                  child: const Text('취소'),
                ),
                TextButton(
                  onPressed: () {
                    Navigator.pop(context, 'OK');
                    String newTodo = _contentEditController.text;
                    Provider.of<Todolist>(context, listen: false)
                        .addTodo(newTodo);
                    _contentEditController.clear();
                  },
                  child: const Text('추가'),
                ),
              ],
            ),
          ).then((value) {
            if (value == null) {
              _contentEditController.clear();
              return;
            }
          })
        },
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}

class MyDataTable extends StatelessWidget {
  const MyDataTable({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Todolist todolist = Provider.of<Todolist>(context);
    List<String> todoList = todolist.todo;

    return ListView.builder(
      itemCount: todoList.length,
      itemBuilder: (context, index) {
        TextEditingController _contentEditController2 =
            TextEditingController(text: todoList[index]);
        return ListTile(
          title: Row(
            children: [
              Expanded(
                child: Text(todoList[index]),
              ),
              OutlinedButton(
                onPressed: () {
                  showDialog<String>(
                    context: context,
                    builder: (BuildContext context) => AlertDialog(
                      title: const Text('ToDoList 수정하기'),
                      content: TextField(
                        decoration: InputDecoration(
                          border: OutlineInputBorder(),
                        ),
                        controller: _contentEditController2,
                      ),
                      actions: <Widget>[
                        TextButton(
                          onPressed: () => Navigator.pop(context, 'Cancel'),
                          child: const Text('취소'),
                        ),
                        TextButton(
                          onPressed: () {
                            Navigator.pop(context, 'OK');
                            String newTodo = _contentEditController2.text;
                            Provider.of<Todolist>(context, listen: false)
                                .updateTodo(index, newTodo);
                          },
                          child: const Text('수정'),
                        ),
                      ],
                    ),
                  );
                },
                style: ButtonStyle(
                  foregroundColor:
                      MaterialStateProperty.all<Color>(Colors.white),
                  backgroundColor:
                      MaterialStateProperty.all<Color>(Colors.blue),
                ),
                child: Text('수정'),
              ),
              SizedBox(width: 8), // 간격 조정
              OutlinedButton(
                onPressed: () {
                  Provider.of<Todolist>(context, listen: false)
                      .removeTodo(todoList[index]);
                },
                style: ButtonStyle(
                  foregroundColor:
                      MaterialStateProperty.all<Color>(Colors.white),
                  backgroundColor: MaterialStateProperty.all<Color>(Colors.red),
                ),
                child: Text('삭제'),
              )
            ],
          ),
        );
      },
    );
  }
}
