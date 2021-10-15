package com.tekcapsule.event.domain.service;

import com.tekcapsule.event.domain.command.CreateCommand;
import com.tekcapsule.event.domain.command.DisableCommand;
import com.tekcapsule.event.domain.command.UpdateCommand;
import com.tekcapsule.event.domain.model.Mentor;
import com.tekcapsule.event.domain.query.SearchItem;
import com.tekcapsule.event.domain.query.SearchQuery;

import java.util.List;

public interface CapsuleService {

    Mentor create(CreateCommand createCommand);

    Mentor update(UpdateCommand updateCommand);

    void disable(DisableCommand disableCommand);

    List<SearchItem> search(SearchQuery searchQuery);

    Mentor get(String tenantId, String userId);
}
