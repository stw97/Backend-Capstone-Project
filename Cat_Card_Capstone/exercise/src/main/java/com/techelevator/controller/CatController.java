package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;


    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public CatCard makeNewCard() {
        CatFact f = catFactService.getFact();
        CatPic p = catPicService.getPic();
        CatCard c = new CatCard();
        c.setCatFact(f.getText());
        c.setImgUrl(p.getFile());
        return c;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<CatCard> list() {
        return catCardDao.list();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public CatCard get(@PathVariable int id) {
        return catCardDao.get(id);
    }

    @RequestMapping (method = RequestMethod.POST)
    public void save(@RequestBody CatCard catCard) {
        catCardDao.save(catCard);
    }

    @RequestMapping (value = "/{id}", method = RequestMethod.PUT)
    public boolean update(@Valid @RequestBody CatCard changedCard, @PathVariable long id) {
        return catCardDao.update(id, changedCard);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        catCardDao.delete(id);
    }
}
