
package org.crosswire.jsword.book.search.parse;

import java.util.Iterator;

import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.book.BookUtil;
import org.crosswire.jsword.passage.Passage;

/**
 * The Search Word for a Word to search for. The default if no other SearchWords
 * match.
 * 
 * <p><table border='1' cellPadding='3' cellSpacing='0'>
 * <tr><td bgColor='white' class='TableRowColor'><font size='-7'>
 *
 * Distribution Licence:<br />
 * JSword is free software; you can redistribute it
 * and/or modify it under the terms of the GNU General Public License,
 * version 2 as published by the Free Software Foundation.<br />
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.<br />
 * The License is available on the internet
 * <a href='http://www.gnu.org/copyleft/gpl.html'>here</a>, or by writing to:
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston,
 * MA 02111-1307, USA<br />
 * The copyright to this program is held by it's authors.
 * </font></td></tr></table>
 * @see docs.Licence
 * @author Joe Walker [joe at eireneh dot com]
 * @version $Id$
 */
public class StartsParamWord implements ParamWord
{
    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.search.parse.ParamWord#getWord(org.crosswire.jsword.book.search.parse.Parser)
     */
    public String getWord(LocalParser engine) throws BookException
    {
        throw new BookException(Msg.STARTS_WORD);
    }

    /**
     * WARNING the return from this method is a PassageTally which is not a
     * 100% match for the Passage interface. Maybe this needs to be fixed
     * somehow.
     * @see org.crosswire.jsword.book.search.parse.ParamWord#getPassage(org.crosswire.jsword.book.search.parse.LocalParser)
     */
    public Passage getPassage(LocalParser engine) throws BookException
    {
        if (!engine.iterator().hasNext())
        {
            throw new BookException(Msg.STARTS_BLANK);
        }

        ParamWord param = (ParamWord) engine.iterator().next();
        String word = param.getWord(engine);
        Iterator it = engine.getIndex().getStartsWith(word);
        String[] words = BookUtil.toStringArray(it);

        return engine.getPassage(words);
    }
}