package org.product.webserver.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/api/session")
public class SessionsWS {

    final FindByIndexNameSessionRepository<? extends Session> sessions;

    @Autowired
    public SessionsWS(FindByIndexNameSessionRepository<? extends Session> sessions) {
        this.sessions = sessions;
    }

    @RequestMapping("/")
    public Collection<? extends Session> index(Principal principal, Model model) {
        Collection<? extends Session> usersSessions = this.sessions
                .findByIndexNameAndIndexValue(
                        FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME,
                        principal.getName())
                .values();
        model.addAttribute("sessions", usersSessions);
        return usersSessions;
    }

    @RequestMapping(value = "/{sessionIdToDelete}", method = RequestMethod.DELETE)
    public String removeSession(Principal principal,
                                @PathVariable String sessionIdToDelete) {
        Set<String> usersSessionIds = this.sessions.findByIndexNameAndIndexValue(
                FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME,
                principal.getName()).keySet();
        if (usersSessionIds.contains(sessionIdToDelete)) {
            this.sessions.deleteById(sessionIdToDelete);
        }

        return "redirect:/";
    }
}
