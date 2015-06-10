package model.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import model.entities.Brigade;
import model.entities.Employee;
import model.entities.ExpressService;
import model.entities.Petition;
import model.entities.UtilityWorker;
import model.entities.Work;

import org.apache.log4j.Logger;

import dao.DaoFactory;
import dao.jdbc.JDBCDaoFactory;

public class Dispatcher extends Thread {
	private volatile static Dispatcher instanse;
	private BlockingQueue<Petition> drop;
	private DaoFactory factory = JDBCDaoFactory.getInstance();
	private List<Employee> allEmployee = factory.createEmployeeDao().findAll();
	private static final int SIZE_BUFFER = 10;

	static Logger logger = Logger.getLogger(Dispatcher.class);

	private Dispatcher() {
		logger.info("Create dispatcher.");
		drop = new ArrayBlockingQueue<Petition>(SIZE_BUFFER, true);
	}

	public static Dispatcher getInstanse() {
		if (instanse == null) {
			synchronized (Dispatcher.class) {
				if (instanse == null) {
					instanse = new Dispatcher();
					instanse.start();
					logger.info("Dispatcher have started.");
				}
			}
		}
		return instanse;
	}

	public void addPetition(Petition pet) throws InterruptedException {
		factory.createPetitionDao().create(pet);
		logger.info("Add petition to database.");
		drop.put(pet);
		logger.info("Add petition:" + pet + ", to queue.");
	}

	private Brigade generateBrigade(Petition pet) {
		Brigade brigade = new Brigade();
		brigade.setIdEmployee(generateIdEmployee(pet));
		logger.info("Create Brigate with helping Petition");
		return brigade;
	}

	private int generateIdEmployee(Petition pet) {
		UtilityWorker currentUtilityWorker = getUtilityWorker(pet);
		List<Employee> allEmployeeForCurrentJob = new ArrayList<>();
		for (Employee e : allEmployee) {
			if (e.getUtilityWorker().equals(currentUtilityWorker)) {
				allEmployeeForCurrentJob.add(e);
			}
		}
		Random rand = new Random();

		Employee resultEmployee = allEmployeeForCurrentJob.get(rand
				.nextInt(allEmployeeForCurrentJob.size()));
		return resultEmployee.getId();
	}

	private Work generateWork(Petition pet, Brigade brigade) {
		Work work = new Work();

		work.setTime(pet.getTime());// ���������� ����� �� �������� � �� �������
									// ���������
		work.setAdress(factory.createTenantDao().find(pet.getIdUser())
				.getAdress());// ����� � �����
		work.setIdPetition(pet.getId());
		work.setIdBrigade(brigade.getId());

		logger.info("Create Work.(petition + brigade)");
		return work;
	}

	private UtilityWorker getUtilityWorker(Petition pet) {
		UtilityWorker uw = null;

		ExpressService es = pet.getExpressService();
		switch (es) {
		case ELECTRICAL:
			uw = UtilityWorker.ELECTRICIAN;
			break;
		case SANITARY_ENGINEERING:
			uw = UtilityWorker.SANITARY_TECHNICIAN;
			break;
		case WOOD_WORK:
			uw = UtilityWorker.JOINER;
			break;
		}
		logger.debug("Switch UtilityWorker: " + uw.name());
		return uw;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Petition pet = drop.take();
				logger.info("Take petition:" + pet + ", with queue.");
				Brigade brigade = generateBrigade(pet);
				factory.createBrigadeDao().create(brigade);
				logger.info("Add brigade to database.");

				Work work = generateWork(pet, brigade);
				factory.createWorkDao().create(work);
				logger.info("Add work to database.");

			} catch (InterruptedException e) {
				logger.error("Error:" + e);
			}
		}
	}
}
