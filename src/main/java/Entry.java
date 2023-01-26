import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Entry {
    private JFrame frame;
    private JMenuBar menuBar;
    private final MenuActionListener listener = new MenuActionListener();
    private final Color menuColour = new Color(120,120,120);
    private void loadIcon()
    {
        ImageIcon img = resize("src/main/resources/PhoenixIcon.png" ,32,32);
        frame.setIconImage( img.getImage() );
    }
    private ImageIcon resize(String filename,int width,int height){
        ImageIcon img = new ImageIcon(filename);
        Image icon = img.getImage();
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(icon, 0, 0, width,height , null);
        return new ImageIcon(bi);
    }
    private void createMenus(){
        menuBar = new JMenuBar();
        JMenu menu = createMenu("File","New Project","Open Project","Save All");
        JMenu newMenu = createMenu("New","Class","Package","Existing Package");
        menu.add(newMenu);
        addChildren(menu,"Exit");
        menuBar.add(menu);
        menuBar.add(createMenu("Code","Reformat","Issues","Refactor","Class Structure","Formatting Options","Optimise Imports"));
        menuBar.add(createMenu("Tools","Generate Javadoc","Export to jar","Keybindings"));
        menuBar.add(createMenu("Help","About"));
    }
    public void launch(){
        FlatDarkLaf.setup();
        frame = new JFrame( "Phoenix" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        loadIcon();
        createMenus();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabLayoutPolicy( JTabbedPane.SCROLL_TAB_LAYOUT );

        String[] arr = (new File("src/main/java")).list( (dir, name ) -> name.endsWith( ".java" ) );
        if (arr != null) {
            for(String str:arr){
                JTextPane textPane = new JTextPane();
                try
                {
                    StringBuilder text = new StringBuilder();
                    FileReader reader= new FileReader("src/main/java/"+str);
                    int i;
                    while( (i = reader.read())!=-1){
                        text.append( (char) i );
                    }
                    textPane.setText( text.toString() );
                    reader.close();
                }
                catch ( IOException e )
                {
                    e.printStackTrace();
                }
                JScrollPane pane = new JScrollPane( textPane );
                pane.setBorder( BorderFactory.createEmptyBorder() );

                ImageIcon img = resize("src/main/resources/ClassIcon.png",16,16);

                tabbedPane.addTab( str,img,pane );
            }
        }
        frame.setJMenuBar(menuBar);
        frame.add(tabbedPane);
        frame.setSize(600,600);
        frame.setVisible(true);

    }
    public static void main(String[] args){
        new Entry().launch();
    }
    private String format(String in){
        return "    "+in+"    ";
    }
    private JMenuItem createMenuItem(String title){
        JMenuItem menuItem = new JMenuItem(format(title));
        menuItem.addActionListener(listener);
        return menuItem;
    }
    private void addChildren(JMenu menu,String... children){
        for(String str:children){
            menu.add(createMenuItem(str));
        }
    }
    private JMenu createMenu(String title, String...children){
        JMenu menu = new JMenu(format(title));
        menu.setOpaque(true);
        menu.setBorder(BorderFactory.createMatteBorder(0,0,0,1,menuColour));
        addChildren(menu,children);
        return menu;
    }
    private static class MenuActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
