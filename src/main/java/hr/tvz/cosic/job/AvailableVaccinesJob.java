package hr.tvz.cosic.job;

import hr.tvz.cosic.service.VaccineDTO;
import hr.tvz.cosic.service.VaccineService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class AvailableVaccinesJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(AvailableVaccinesJob.class);

    private final VaccineService vaccineService;

    public AvailableVaccinesJob(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @Override
    @Scheduled(cron = "0 0 12 * * MON-FRI")
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        final List<VaccineDTO> vaccineDTOS = vaccineService.findAll();

        if(vaccineDTOS.isEmpty()){
            logger.info("Nema cjepiva!");
        } else {
            logger.info("Trenutno dostupna cjepiva");
            logger.info("------------------------------");
            vaccineDTOS.forEach(
                    vaccine -> logger.info(vaccine.getManufacturerName() + " - " + vaccine.getAvailableDoses())
            );
            logger.info("------------------------------");
        }

    }
}
