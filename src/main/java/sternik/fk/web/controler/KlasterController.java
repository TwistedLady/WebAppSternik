package sternik.fk.web.controler;



import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sternik.fk.entities.Cd;
import sternik.fk.entities.Status;
import sternik.fk.services.KlasterService;
import sternik.fk.services.NotificationService;

@Controller
public class KlasterController {

    @Autowired
    @Qualifier("spring")
    private KlasterService klaserService;
    @Autowired
    private NotificationService notificationService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }

    @ModelAttribute("cdAll")
    public List<Cd> populateCoins() {
        return this.klaserService.findAll();
    }

    @ModelAttribute("cdToSell")
    public List<Cd> populateCoinsToSell() {
        return this.klaserService.findAllToSell();
    }

    @ModelAttribute("cdLast3")
    public List<Cd> populateLast3Coins() {
        return this.klaserService.findLatest3();
    }

    @RequestMapping({ "/", "/index" })
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/cds", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("MyMessages",  notificationService.getNotificationMessages());
        return "klaser";
    }

    @RequestMapping("/tosell")
    public String showToSellPage() {
        return "tosell";
    }
}
