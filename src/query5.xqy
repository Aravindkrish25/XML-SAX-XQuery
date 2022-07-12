for $x at $i in doc("src//employee.xml")/employees/employee
where $x/project/price>100
return <sells>Employee ID {data($x/@id)} sells {data($x/project/product)} </sells>
