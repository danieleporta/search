package nl.xs4all.banaan.tst8.web.subclass;

/** 
 * Customize a reusable component.
 * The interesting bit is that this does not need
 * its own HTML file.
 */
public class DerivedComponent extends BaseComponent {
    private static final long serialVersionUID = 1L;

    public DerivedComponent(String id) {
        super(id, "blah");
    }

}
