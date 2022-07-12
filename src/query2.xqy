for $x at $i in doc("src//employee.xml")/employees/employee
return <name>{$i}.{data($x/firstname)}_{data($x/lastname)}</name>
