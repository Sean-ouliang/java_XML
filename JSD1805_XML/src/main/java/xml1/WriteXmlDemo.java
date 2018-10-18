package xml1;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class WriteXmlDemo {
	public static void main(String[] args){
		List<Emp> list = new ArrayList<Emp>();
		Scanner scan = new Scanner(System.in);
		int count = 1;
		while(true){
			System.out.println("请输入第"+ count++ +"位员工的信息");
			String line = scan.nextLine();
			String[] data = line.split(",");
			if(data.length>1){
				int id = Integer.parseInt(data[0]);
				String name = data[1];
				int age = Integer.parseInt(data[2]);
				String gender = data[3];
				int salary = Integer.parseInt(data[4]);
				Emp emp = new Emp(id, name, age, gender, salary);
				list.add(emp);
			}
			if("exit".equals(line)){
				System.out.println("信息输入完毕");
				break;
			}
		}
		try{
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("list");
			for(Emp emp : list){
				Element empEle = root.addElement("emp").addAttribute(
						"id",emp.getId()+"");
				empEle.addElement("name").addText(emp.getName());
				empEle.addElement("age").addText(emp.getAge()+"");
				empEle.addElement("gender").addText(emp.getGender());
				empEle.addElement("salary").addText(emp.getSalary()+"");
			}
			XMLWriter writer = new XMLWriter(new FileOutputStream("emp.xml"),
					OutputFormat.createPrettyPrint());
			writer.write(doc);
			System.out.println("信息录入完毕");
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
