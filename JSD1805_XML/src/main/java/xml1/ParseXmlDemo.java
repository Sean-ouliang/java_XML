package xml1;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseXmlDemo {
	public static void main(String[] args){
		List<Emp> list = new ArrayList<Emp>();
		try{
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new FileInputStream("emplist.xml"));
			Element root = doc.getRootElement();
			List<Element> emps = root.elements("emp");
			for(Element empEle : emps){
				int id = Integer.parseInt(empEle.attributeValue("id"));
				String name = empEle.elementText("name");
				int age = Integer.parseInt(empEle.elementText("age"));
				String gender = empEle.elementText("gender");
				int salary = Integer.parseInt(empEle.elementText("salary"));
				Emp emp = new Emp(id,name,age,gender,salary);
				list.add(emp);
			}
			System.out.println("解析完毕");
			System.out.println("获得了"+list.size()+"位员工");
			for(Emp e : list){
				System.out.println(e);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
