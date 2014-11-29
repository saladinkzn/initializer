package ru.shadam.initializer.plugin;

import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.project.Project;

import java.util.List;

/**
 * Base class for Initializer plugin.
 */
public abstract class Plugin {

    /**
     * Registers plugin into project.
     * It is intended for registering configurations and checking for or applying another plugins.
     *
     * @param project project to register plugin into
     */
    public abstract void register(Project project);

    /**
     * Configuring stage of project executing
     * It is intended for configuring other plugins
     * Optional method.
     *
     * @param project project to configure
     */
    public void configure(Project project) {}

    /**
     * Generating stage of project executing
     * It is intended for generating files.
     * NOTE: prefer configuring other plugins instead of trying to generate all files by yourself
     *
     * @param project project to generate files for
     * @return list of files, not null.
     */
    public abstract List<File> generateFiles(Project project);
}
