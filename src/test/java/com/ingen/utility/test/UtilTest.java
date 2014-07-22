package com.ingen.utility.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.ingen.utility.EntityToVoTransformer;
import com.ingen.utility.entity.Book;
import com.ingen.utility.entity.BookVO;


/**
 * Testcase to check the transform methods behavior
 * 
 * @version 1.0.0
 * @since Jul 2014
 * 
 * */
//@RunWith(JUnit4.class)
public class UtilTest extends TestCase {

	private EntityToVoTransformer<Book, BookVO> entityTransformer;
	
	@Before
	public void setUp(){
		entityTransformer = new EntityToVoTransformer<Book, BookVO>(Book.class, BookVO.class);;
	}
	
	@Test
	public void testEntityToVO(){
			
		Book book = new Book();
		
		book.setAuthor("Anand");
		book.setIsbn("ISBN01");
		book.setTitle("Transformer");
		
		book.setPublishedDate("22/07/2014");
		
		BookVO bookVO = (BookVO) entityTransformer.toValueObject(book);
		
		assertNotNull("Transformation assertation check", bookVO);
		
		System.out.println(" Object Test :"+bookVO.getPublishedDate());
		
	}
	
	
	
	@Test
	public void testCollEntityToVO(){
				
		Book book = new Book();
		
		book.setAuthor("Anand");
		book.setIsbn("ISBN01");
		book.setTitle("Transformer");
		
		book.setPublishedDate("22/07/2014");
		
		List<Book> lstBookEntity = new ArrayList<Book>();
		
		lstBookEntity.add(book);
		
		List<BookVO> lstBookVO = (List) entityTransformer.collEntityToVo(lstBookEntity);
		
		
		assertNotNull("Transformation assertation check", lstBookVO);
		
		for(BookVO bookVO : lstBookVO){
			System.out.println("Coll test : "+bookVO.getAuthor());
		}
		
	}
	
}
