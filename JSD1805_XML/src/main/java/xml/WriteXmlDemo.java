package xml;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * 使用DOM生成XML文档
 * @author tarena
 *
 */
public class WriteXmlDemo {
	public static void main(String[] args){
		/*
		 * 生成一个XML文档的大致步骤
		 * 1.创建一个Document实例，表示一个空白文档
		 * 2.向Document实例中添加根元素
		 * 3.按照预定生成的XML文档结构向根元素中逐级
		 *   添加子元素以及对应数据
		 * 4.创建XmlWriter
		 * 5.通过XmlWriter将Document写出以生成XML文档
		 */
		List<Emp> list = new ArrayList<Emp>();
		list.add(new Emp(1,"张三",22,"男",5000));
		list.add(new Emp(2,"李四",23,"女",6000));
		list.add(new Emp(3,"王五",24,"男",7000));
		list.add(new Emp(4,"赵六",25,"女",8000));
		list.add(new Emp(5,"钱七",26,"男",9000));
		
		try {
			//1创建一个空白文档
			Document doc = DocumentHelper.createDocument();
			/*
			 * 2
			 * Document提供了添加根元素的方法：
			 * Element addElement(String name)
			 * 向当前文档中添加给定名字的元素，由于一个
			 * 文档中只能有一个跟元素，所以该方法只能调用
			 * 一次。该方法返回一个Element实例，该
			 * 实例就表示天添加的元素，以便对其继续追加子元素
			 * 等操作。
			 */
			Element root = doc.addElement("list");
			/*
			 * 3
			 * 将集合中每个Emp实例以一个<emp>元素的形成
			 * 添加到根元素<list>中
			 */
			/**
			 * 基本类型转换成字符串最简单的方法就是在后面拼上一个空字符串
			 */
			for(Emp emp:list){
				//向根标签中添加自标签emp
				Element empEle = root.addElement("emp");
				//添加名字
				Element nameEle = empEle.addElement("name");
				nameEle.addText(emp.getName());
				//添加年龄
				Element ageEle = empEle.addElement("age");
				ageEle.addText(emp.getAge()+"");
				//添加性别
				Element genderEle = empEle.addElement("gender");
				genderEle.addText(emp.getGender());
				//添加工资
				Element salaryEle = empEle.addElement("salary");
				salaryEle.addText(emp.getSalary()+"");
				
				//添加id（属性）
				empEle.addAttribute("id",emp.getId()+"");
			}
			//4
			XMLWriter writer = new XMLWriter(
					new FileOutputStream("myemp.xml"),
					OutputFormat.createPrettyPrint()
			);
			//5
			writer.write(doc);
			System.out.println("写出完毕");
			writer.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
