package xml;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 解析XML文档
 * @author tarena
 *
 */
public class ParseXmlDemo {
	public static void main(String[] args) {
		List<Emp> list = new ArrayList<Emp>();
		/*
		 * 使用DOM解析XML的大致步骤
		 * 1.创建SAXReader
		 * 2.使用SAXReader读取指定的xml文档并生成
		 *   Document对象
		 *   这一步就是DOM解析耗时耗资源的地方，因为
		 *   要现将XML文档全部读取完毕并以Document对象
		 *   形式保存在内存中
		 * 3.通过Document获取根元素
		 * 4.根据XML文档的结构逐级获取子元素以达到遍历
		 *   XML文档的目的
		 */
		try {
			/*
			 * org.dom4j.io.SAXReader
			 * 如果eclipse提示找不到这个类，那么原因在于
			 * 没有导入dom4j的jar包
			 * 
			 * 1.创建SAXReader
			 */
			SAXReader reader = new SAXReader();
			//2
//			Document doc = reader.read(new File("emplist.xml"));
			Document doc = reader.read(new FileInputStream("emplist.xml"));
			/*
			 * 3
			 * Document提供了获取根元素的方法：
			 * Element getRootElement()
			 * 
			 * Element的每一个实例用于表示XML文档中的一个元素（一对标签）
			 * Element提供了获取其表示的元素的相关方法常用的有：
			 * 
			 * String getName()
			 * 获取当前标签的名字
			 * 
			 * String getText()
			 * 获取当前标签中间的文本，例如:<a>获取的文本</a>
			 * 
			 * Element element(String name)
			 * 获取当前标签中指定名字的字标签
			 * 
			 * List elements()
			 * 获取当前标签中所有子标签
			 * 
			 * List elements(String name)
			 * 获取当前标签中所有同名（指定的名字）子标签
			 */
			Element root = doc.getRootElement();
			/*
			 * 获取根标签<list>中所有的<emp>标签
			 */
			List<Element> empList = root.elements("emp");
			for(Element empEle:empList){
				/*
				 * 获取<emp id="xx">标签的属性id对应的值
				 * Element提供了获取属性的方法：
				 * Attribute attribute(String name)
				 * 获取指定名字的属性
				 * 
				 * Attribute的每个实例用于表示某个标签中的一个属性
				 * 其有两个常用方法：
				 * String getName():获取属性的名字
				 * String getValue():获取属性的值
				 * 
				 * Element也提供了快捷获取属性值的方法：
				 * String attributeValue(String name)
				 * 获取当前标签中指定名字对应属性的值
				 */
				//获取ID
//				Attribute attr = empEle.attribute("id");
//				int id = Integer.parseInt(attr.getValue());
				int id = Integer.parseInt(empEle.attributeValue("id"));
				//获取名字
				Element nameEle = empEle.element("name");
				String name = nameEle.getText();
				//获取年龄
				int age = Integer.parseInt(empEle.elementText("age"));
				//获取性别
				String gender = empEle.elementText("gender");
				//获取工资
				int salary = Integer.parseInt(empEle.elementText("salary"));
				Emp emp = new Emp(id,name,age,gender,salary);
				list.add(emp);
			}//loop end
			System.out.println("解析完毕！");
			System.out.println("获取了"+list.size()+"个员工");
			for(Emp e:list){
				System.out.println(e);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}









