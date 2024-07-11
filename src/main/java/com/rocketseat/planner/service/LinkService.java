package com.rocketseat.planner.service;

import com.rocketseat.planner.dto.link.LinkData;
import com.rocketseat.planner.dto.link.LinkRequestPayload;
import com.rocketseat.planner.dto.link.LinkResponse;
import com.rocketseat.planner.model.link.Link;
import com.rocketseat.planner.model.trip.Trip;
import com.rocketseat.planner.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    public LinkResponse createLink(LinkRequestPayload payload, Trip trip){
        Link newLink = new Link(payload.title(), payload.url(), trip);
        linkRepository.save(newLink);
        return new LinkResponse(newLink.getId());
    }

    public List<LinkData> findAllLinks(UUID tripId){
        List<Link> linkList = new ArrayList<>(linkRepository.findByTripId(tripId));
        return linkList.stream().map(link -> new LinkData(link.getId(), link.getTitle(), link.getUrl())).toList();
    }
}
