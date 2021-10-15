package com.tekcapsule.event.domain.service;

import com.tekcapsule.event.domain.command.CreateCommand;
import com.tekcapsule.event.domain.command.DisableCommand;
import com.tekcapsule.event.domain.command.UpdateCommand;
import com.tekcapsule.event.domain.model.Event;
import com.tekcapsule.event.domain.query.SearchItem;
import com.tekcapsule.event.domain.query.SearchQuery;
import com.tekcapsule.event.domain.repository.CapsuleDynamoRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CapsuleServiceImpl implements CapsuleService {
    private CapsuleDynamoRepository mentorRepository;

    @Autowired
    public CapsuleServiceImpl(CapsuleDynamoRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    @Override
    public Event create(CreateCommand createCommand) {

        log.info(String.format("Entering create mentor service - Tenant Id:{0}, Name:{1}", createCommand.getTenantId(), createCommand.getName().toString()));

        Name name = createCommand.getName();
        if (name != null) {
            name.setDisplayName(String.format("{0} {1}", name.getFirstName(), name.getLastName()));
        }
        DateOfBirth dateOfBirth = createCommand.getDateOfBirth();
        if (dateOfBirth != null) {
            dateOfBirth.setDateOfBirth(String.format("{0}/{1}/{2}", dateOfBirth.getDay(), dateOfBirth.getMonth(), dateOfBirth.getYear()));
        }
        Event event = Event.builder()
                .active(true)
                .activeSince(DateTime.now(DateTimeZone.UTC).toString())
                .blocked(false)
                .awards(createCommand.getAwards())
                .certifications(createCommand.getCertifications())
                .contact(createCommand.getContact())
                .dateOfBirth(dateOfBirth)
                .educationalQualifications(createCommand.getEducationalQualifications())
                .headLine(createCommand.getHeadLine())
                .name(name)
                .professionalExperiences(createCommand.getProfessionalExperiences())
                .publications(createCommand.getPublications())
                .social(createCommand.getSocial())
                .tags(createCommand.getTags())
                .tenantId(createCommand.getTenantId())
                .userId(createCommand.getContact().getEmailId())
                .build();

        event.setAddedOn(createCommand.getExecOn());
        event.setUpdatedOn(createCommand.getExecOn());
        event.setAddedBy(createCommand.getExecBy().getUserId());

        return mentorRepository.save(event);
    }

    @Override
    public Event update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update mentor service - Tenant Id:{0}, User Id:{1}", updateCommand.getTenantId(), updateCommand.getUserId()));

        Event event = mentorRepository.findBy(updateCommand.getTenantId(), updateCommand.getUserId());
        if (event != null) {
            event.setAwards(updateCommand.getAwards());
            event.setHeadLine(updateCommand.getHeadLine());
            event.setContact(updateCommand.getContact());
            event.setCertifications(updateCommand.getCertifications());
            event.setPhotoUrl(updateCommand.getPhotoUrl());
            event.setTags(updateCommand.getTags());
            event.setSocial(updateCommand.getSocial());
            event.setEducationalQualifications(updateCommand.getEducationalQualifications());
            event.setProfessionalExperiences(updateCommand.getProfessionalExperiences());
            event.setPublications(updateCommand.getPublications());

            event.setUpdatedOn(updateCommand.getExecOn());
            event.setUpdatedBy(updateCommand.getExecBy().getUserId());

            mentorRepository.save(event);
        }
        return event;
    }

    @Override
    public void disable(DisableCommand disableCommand) {

        log.info(String.format("Entering disable mentor service - Tenant Id:{0}, User Id:{1}", disableCommand.getTenantId(), disableCommand.getUserId()));

        mentorRepository.disableById(disableCommand.getTenantId(), disableCommand.getUserId());
    }

    @Override
    public List<SearchItem> search(SearchQuery searchQuery) {

        log.info(String.format("Entering search mentor service - Tenant Id:{0}", searchQuery.getTenantId()));

        return mentorRepository.search(searchQuery.getTenantId());
    }

    @Override
    public Event get(String tenantId, String userId) {

        log.info(String.format("Entering get mentor service - Tenant Id:{0}, User Id:{1}", tenantId, userId));

        return mentorRepository.findBy(tenantId, userId);
    }
}
