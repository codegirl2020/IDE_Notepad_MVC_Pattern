
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class DocumentPrintable   implements Printable {
    private String textForPrint;
    private String[] arrayRows;
    private int[] pageBreaks;

    public DocumentPrintable(String textForPrint){
            this.textForPrint = textForPrint;
            arrayRows = textForPrint.split("\n");
    }
    public int print(Graphics g, PageFormat pf, int pageIndex)
            throws PrinterException{
        int y = 50;
        int x = 50;

        Font font = new Font("Serif", Font.PLAIN, 14);
        FontMetrics metrics = g.getFontMetrics(font);
        int lineHeight = metrics.getHeight();

        //Page margins on the sides

        int paddingWidthPage = x * 2;
        int pageWidth = (int)(pf.getImageableHeight()) - paddingWidthPage;
        int pageHeight = (int) pf.getImageableHeight() - (y * 2);

        if (pageBreaks == null) {
            //Text processing
//            initTextLines();

//
            int linesPerPage = pageHeight / lineHeight;
            int numBreaks = (arrayRows.length-1)/linesPerPage;
            pageBreaks = new int[numBreaks];
            for (int b=0; b<numBreaks; b++) {
                pageBreaks[b] = (b+1)*linesPerPage;
            }
        }
        if (pageIndex > pageBreaks.length) {
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         * Since we are drawing text we
         */

        String textPageNumber = "1";
        String textNameTeam = "Page";

       int pageNumberX = (int) pf.getImageableWidth() - metrics.stringWidth(textPageNumber);
       int pageNumberY = (int) pf.getImageableHeight() - y / 2;

        //print team name
        g.setColor(Color.BLUE);
        g.setFont(font);
        g.drawString(textNameTeam, x, pageNumberY);

        //print page number
        g.setColor(Color.BLUE);
        g.setFont(font);
        g.drawString(textPageNumber + (pageIndex + 1), pageNumberX, pageNumberY);

        //print text lines
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);

        /* Draw each line that is on this page.
         * Increment 'y' position by lineHeight for each line.
         */

        int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex-1];
        int end   = (pageIndex == pageBreaks.length)
                ?  arrayRows.length : pageBreaks[pageIndex];
        for (int line=start; line<end; line++) {
            y += lineHeight;
            g.drawString( arrayRows[line], x, y);
        }
        return PAGE_EXISTS;

    }
}
