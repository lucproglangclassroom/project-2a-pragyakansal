package imperative.simple

/** Package-wide typesafe equality instance to help with readLine. */
given CanEqual[String | Null, Null] = CanEqual.derived
