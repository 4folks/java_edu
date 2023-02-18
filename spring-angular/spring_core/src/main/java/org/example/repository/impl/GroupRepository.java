package org.example.repository.impl;

import org.example.db_config.HibernateUtil;
import org.example.model.member.Group;
import org.example.repository.GeneralCrud;
import org.hibernate.SessionFactory;

public class GroupRepository extends GeneralCrud<Group> {
    public GroupRepository() {
        super(Group.class);
    }
}