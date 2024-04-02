package com.example.tourweb;

//import com.example.tourweb.entity.Product;
//import com.example.tourweb.repository.ProductRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TourWebApplication {

//@Autowired
//private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(TourWebApplication.class, args);
	}
//	@PostConstruct
//	public void saveProduct() {
//		var product1 = Product.builder()
//				.name("mumbai")
//				.price(90)
//				.imageUrl("/images/p-1.jpg")
//				.description("Tham quan Cổng Ấn Độ, một công trình kiến trúc nổi bật của Mumbai. Đặt vé ngay hôm nay")
//				.build();
//		var product2 = Product.builder()
//				.name("hawaii")
//				.imageUrl("/images/p-2.jpg")
//				.description("một thiên đường nhiệt đới với những bãi biển tuyệt đẹp và những khu rừng nhiệt đới")
//				.price(85)
//				.build();
//		var product3 = Product.builder()
//				.name("sydney")
//				.imageUrl("/images/p-3.jpg")
//				.description("một thành phố hiện đại và đầy sức sống, với những công trình kiến trúc nổi tiếng")
//				.price(70)
//				.build();
//		var product4 = Product.builder()
//				.name("paris")
//				.price(110)
//				.imageUrl("/images/p-4.jpg")
//				.description("một trong những thành phố du lịch nổi tiếng nhất thế giới, với những địa điểm du lịch nổi tiếng")
//				.build();
//		var product5 = Product.builder()
//				.name("tokyo")
//				.imageUrl("/images/p-5.jpg")
//				.description("một thành phố độc đáo và đa dạng, nơi giao thoa giữa truyền thống và hiện đại")
//				.price(75)
//				.build();
//		var product6 = Product.builder()
//				.name("eypt")
//				.price(65)
//				.imageUrl("/images/p-6.jpg")
//				.description("một nền văn minh cổ xưa, với những di tích nổi bật như kim tự tháp")
//				.build();
//		var product7 = Product.builder()
//				.name("vinhhalong")
//				.imageUrl("/images/p-7.png")
//				.description("Vịnh Hạ Long. Đặt vé ngay hôm nay")
//				.price(100)
//				.build();
//		var product8 = Product.builder()
//				.name("nuibaden")
//				.imageUrl("/images/p-8.png")
//				.description("Tham quan danh lam thắng cảnh ở Tây Ninh. Đặt vé ngay hôm nay")
//				.price(150)
//				.build();
//		var product10 = Product.builder()
//				.name("laithuong")
//				.imageUrl("/images/p-10.png")
//				.description("Quê hương chủ page. Đặt vé ngay hôm nay")
//				.price(399)
//				.build();
//		productRepository.save(product1);
//		productRepository.save(product2);
//		productRepository.save(product3);
//		productRepository.save(product4);
//		productRepository.save(product5);
//		productRepository.save(product6);
//		productRepository.save(product7);
//		productRepository.save(product8);
//		productRepository.save(product10);

	//}
}
