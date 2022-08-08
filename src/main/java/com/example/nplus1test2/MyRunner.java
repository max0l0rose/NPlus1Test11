package com.example.nplus1test2;

import com.example.nplus1test2.dto.IDto1;
import com.example.nplus1test2.model.Beach;
import com.example.nplus1test2.model.Boat;
import com.example.nplus1test2.model.Lifeguard;
import com.example.nplus1test2.repository.BeachRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

@Configuration
@AllArgsConstructor
class MyRunner implements CommandLineRunner {

	final BeachRepository beachRepository;
	//Beach beach = new Beach();

	final EntityManager entityManager;

	@Transactional
	@Override
	public void run(String... args) throws Exception {

		//List<IDto1> beachList = beachRepository.beachesAndLifeguardsCount(); // JPQL
		//List<Beach> beachList = beachRepository.simple(""); // JPQL
		List<Beach> beachList = beachRepository.queryByNameStartingWith(""); // JPQL

//		List<Beach> beachList = beachRepository.queryByNameStartingWith(""
//				//, EntityGraphs.named("Product.brand")
//		);
		beachList.forEach(b -> {
			System.out.println(b.getId() + " " + b.getName() + " "// + b.getLifeguards().size()
					//+ b.getLCount()
			);
			System.out.println();
			List<Lifeguard> lifeguards = b.getLifeguards();

			int size = lifeguards.size();
			System.out.println("size = " + size);

			lifeguards.forEach( l -> {
						System.out.println(l.getId() + " " + l.getName());
						Boat boat = l.getBoat();

						System.out.println("Bt Speed: " + boat.getSpeed());
					}
			);
			System.out.println();
		});

//
//		System.out.println();
//
//		beachList.forEach(b2 -> {
//			System.out.println(b2.getId() + " " + b2.getName() + " " + b2.getLifeguards().size()
//			);
//		});


//		List<Beach> beachList2 = beachRepository.queryByNameStartingWith("M");
//		beachList2.forEach(b -> System.out.println(b.getId() + " " + b.getName()
//				                                           + " " + b.getLifeguards().size()
//		));


//		// dynamic entity graph
//		EntityGraph graph = entityManager.getEntityGraph("Beach.lifeguards"); //.createEntityGraph(Beach.class);
//		//graph.addAttributeNodes("tags"); //here you can add or not the tags
//
//		Map<String, Object> hints = new HashMap();
//		//ints.put("javax.persistence.loadgraph", graph);
//
//		Beach res = entityManager.find(Beach.class, 1L, hints);

		int a = 1;
	}
}
