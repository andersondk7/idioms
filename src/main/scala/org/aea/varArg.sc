def combine(args: String*) = args.mkString("\n")

combine("a", "long", "list")

val items = List("apples", "oranges", "lemons")

combine(items: _*)  // use the ': _*' to convert the collection into a var arg
