for $x in doc("src//employee.xml")/employees/employee
where $x/project/product = "Camera"
return $x