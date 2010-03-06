package nl.xs4all.banaan.tst8.service;

import java.util.List;

import nl.xs4all.banaan.tst8.util.Assoc;

public interface ParamReader {
    /**
     * read strings from ServletContext init parameters.
     */
    public abstract List<Assoc<String>> read();
}
