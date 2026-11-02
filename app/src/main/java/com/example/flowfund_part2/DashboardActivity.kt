
package com.example.flowfund_part2

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Displays the main FlowFund dashboard.
 *
 * Provides navigation to notifications, stokvel group settings,
 * and the upcoming payout schedule.
 */
class DashboardActivity : AppCompatActivity() {

    /**
     * Initializes the dashboard and configures its navigation controls.
     *
     * @param savedInstanceState Previously saved activity state, if available.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Opens the missed payments screen when notifications are selected.
        findViewById<android.view.View>(R.id.btnNotifications)
            .setOnClickListener {
                openScreen(MissedPaymentsActivity::class.java)
            }

        // Opens group settings when the user selects "View all groups".
        findViewById<TextView>(R.id.txtViewAllGroups)
            .setOnClickListener {
                openScreen(GroupSettingsActivity::class.java)
            }

        // Opens settings for the Family Stokvel group.
        findViewById<LinearLayout>(R.id.cardFamilyStokvel)
            .setOnClickListener {
                openGroupSettings("Family Stokvel")
            }

        // Opens settings for the Friends Fund group.
        findViewById<LinearLayout>(R.id.cardFriendsFund)
            .setOnClickListener {
                openGroupSettings("Friends Fund")
            }

        // Opens the payout schedule from the upcoming contribution card.
        findViewById<LinearLayout>(R.id.cardUpcomingContribution)
            .setOnClickListener {
                openScreen(PayoutScheduleActivity::class.java)
            }
    }

    /**
     * Opens the specified activity.
     *
     * @param destination The activity class to open.
     */
    private fun openScreen(destination: Class<*>) {
        startActivity(Intent(this, destination))
    }

    /**
     * Opens group settings for the selected stokvel group.
     *
     * Passes the group name to GroupSettingsActivity through an intent extra.
     *
     * @param groupName The name of the group whose settings should be opened.
     */
    private fun openGroupSettings(groupName: String) {
        val intent = Intent(this, GroupSettingsActivity::class.java)

        // Passes the selected group name to the destination activity.
        intent.putExtra("group_name", groupName)

        startActivity(intent)
    }
}
