import 'package:flutter/material.dart';


class Todolist with ChangeNotifier {
  List<String> _todo = [];

  List<String> get todo => _todo;

  void addTodo(String todo) {
    _todo.add(todo);
    notifyListeners();
  }

  void removeTodo(String todo) {
    _todo.remove(todo);
    notifyListeners();
  }

  void updateTodo(int index, String updatedTodo) {
    if (index >= 0 && index < _todo.length) {
      _todo[index] = updatedTodo;
      notifyListeners();
    }
  }
}