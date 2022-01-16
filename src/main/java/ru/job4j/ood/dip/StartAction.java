package ru.job4j.ood.dip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartAction implements Action {
    private static final Logger LOG = LoggerFactory.getLogger(StartAction.class.getName());

    @Override
    public void doAction() {
        LOG.info("Let's do something action");
    }
}