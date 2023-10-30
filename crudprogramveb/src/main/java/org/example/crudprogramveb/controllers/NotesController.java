package org.example.crudprogramveb.controllers;

import org.example.crudprogramveb.models.WebNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.example.crudprogramveb.dao.WebNoteDAO;

@Controller
@RequestMapping("/")
public class NotesController {

    private final WebNoteDAO webNoteDAO;

    @Autowired
    public NotesController(WebNoteDAO webNoteDAO) {
        this.webNoteDAO = webNoteDAO;
    }

    @GetMapping()
    public String helloPage() {
        return "notes/helloPage";
    }

    @GetMapping("notes")
    public String index(Model model) {
        model.addAttribute("notes", webNoteDAO.index());
        return "notes/index";
    }

    @GetMapping("notes/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("note", webNoteDAO.show(id));
        return "notes/show";
    }

    @GetMapping("notes/new")
    public String newNote(Model model) {
        model.addAttribute("webNote", new WebNote());
        return "notes/new";
    }

    @PostMapping("notes")
    public String create(@ModelAttribute("webNote") WebNote webNote) {
        webNoteDAO.save(webNote);
        return "redirect:/notes";
    }

    @GetMapping("notes/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("webNote", webNoteDAO.show(id));
        return "notes/edit";
    }

    @PatchMapping("notes/{id}")
    public String update(@ModelAttribute("webNote") WebNote webNote, @PathVariable("id") int id) {
        webNoteDAO.update(id, webNote);
        return "redirect:/notes";
    }

    @DeleteMapping("notes/{id}")
    public String delete(@PathVariable("id") int id) {
        webNoteDAO.delete(id);
        return "redirect:/notes";
    }
}
