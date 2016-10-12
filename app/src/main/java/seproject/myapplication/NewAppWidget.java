package seproject.myapplication;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link NewAppWidgetConfigureActivity NewAppWidgetConfigureActivity}
 */
public class NewAppWidget extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null) {
            context.startService(new Intent(context,
                    ToggleService.class));
        } else {
            super.onReceive(context, intent);
        }
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        context.startService(new Intent(context,
                ToggleService.class));
    }

    public static class ToggleService extends IntentService {
        public ToggleService() {
            super("NewAppWidget$ToggleService");
        }

        @Override
        protected void onHandleIntent(Intent intent) {
            ComponentName me = new ComponentName(this, NewAppWidget.class);
            AppWidgetManager mgr = AppWidgetManager.getInstance(this);
            mgr.updateAppWidget(me, buildUpdate(this));
        }

        private RemoteViews buildUpdate(Context context) {

           // CharSequence widgetText = NewAppWidgetConfigureActivity.loadTitlePref(context, appWidgetId);
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            //views.setTextViewText(R.id.appwidget_text, widgetText);

            boolean Encrypting = true;
            if (Encrypting) {
                views.setImageViewResource(R.id.imageView2, R.drawable.icon);
                Encrypting = false;
            } else {
                views.setImageViewResource(R.id.imageView2, R.drawable.iconchange);
                Encrypting = true;
            }
            Intent i = new Intent(this, NewAppWidget.class);
            PendingIntent pi
                    = PendingIntent.getBroadcast(context, 0, i, 0);
            views.setOnClickPendingIntent(R.id.imageView2, pi);

            // Instruct the widget manager to update the widget
           // appWidgetManager.updateAppWidget(appWidgetId, views);

            return views;

        }
    }
}

