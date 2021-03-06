package org.thoughtcrime.SMP.database.loaders;

import android.content.Context;
import android.database.Cursor;

import org.thoughtcrime.SMP.contacts.ContactAccessor;
import org.thoughtcrime.SMP.database.DatabaseFactory;
import org.thoughtcrime.SMP.util.AbstractCursorLoader;

import java.util.List;

public class ConversationListLoader extends AbstractCursorLoader {

  private final String filter;

  public ConversationListLoader(Context context, String filter) {
    super(context);
    this.filter = filter;
  }

  @Override
  public Cursor getCursor() {
    if (filter != null && filter.trim().length() != 0) {
      List<String> numbers = ContactAccessor.getInstance().getNumbersForThreadSearchFilter(context, filter);

      return DatabaseFactory.getThreadDatabase(context).getFilteredConversationList(numbers);
    } else {
      return DatabaseFactory.getThreadDatabase(context).getConversationList();
    }
  }
}
