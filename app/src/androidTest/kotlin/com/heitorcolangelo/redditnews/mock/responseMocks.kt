package com.heitorcolangelo.redditnews.mock

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.heitorcolangelo.repository.model.Content
import com.heitorcolangelo.repository.model.Page
import com.heitorcolangelo.repository.model.ResponseData

fun contentEmptyResponse() = ResponseData<Content>(Page("", listOf()))

fun contentResponse(): ResponseData<Content> {
    val type = object : TypeToken<ResponseData<Content>>() {}.type
    return Gson().fromJson<ResponseData<Content>>(contentMock, type)
}

private const val contentMock = "{\n" +
    "    \"kind\": \"Listing\",\n" +
    "    \"data\": {\n" +
    "        \"after\": \"t3_85706t\",\n" +
    "        \"dist\": 1,\n" +
    "        \"modhash\": \"\",\n" +
    "        \"whitelist_status\": \"all_ads\",\n" +
    "        \"children\": [\n" +
    "            {\n" +
    "                \"kind\": \"t3\",\n" +
    "                \"data\": {\n" +
    "                    \"subreddit_id\": \"t5_2qlqh\",\n" +
    "                    \"approved_at_utc\": null,\n" +
    "                    \"send_replies\": false,\n" +
    "                    \"mod_reason_by\": null,\n" +
    "                    \"banned_by\": null,\n" +
    "                    \"num_reports\": null,\n" +
    "                    \"removal_reason\": null,\n" +
    "                    \"thumbnail_width\": null,\n" +
    "                    \"subreddit\": \"Android\",\n" +
    "                    \"selftext_html\": null,\n" +
    "                    \"selftext\": \"\",\n" +
    "                    \"likes\": null,\n" +
    "                    \"suggested_sort\": null,\n" +
    "                    \"user_reports\": [],\n" +
    "                    \"secure_media\": null,\n" +
    "                    \"is_reddit_media_domain\": false,\n" +
    "                    \"saved\": false,\n" +
    "                    \"id\": \"85706t\",\n" +
    "                    \"banned_at_utc\": null,\n" +
    "                    \"mod_reason_title\": null,\n" +
    "                    \"view_count\": null,\n" +
    "                    \"archived\": false,\n" +
    "                    \"clicked\": false,\n" +
    "                    \"no_follow\": true,\n" +
    "                    \"author\": \"NowHowCow\",\n" +
    "                    \"num_crossposts\": 0,\n" +
    "                    \"link_flair_text\": null,\n" +
    "                    \"mod_reports\": [],\n" +
    "                    \"can_mod_post\": false,\n" +
    "                    \"is_crosspostable\": false,\n" +
    "                    \"pinned\": false,\n" +
    "                    \"score\": 1,\n" +
    "                    \"approved_by\": null,\n" +
    "                    \"over_18\": false,\n" +
    "                    \"report_reasons\": null,\n" +
    "                    \"domain\": \"play.google.com\",\n" +
    "                    \"hidden\": false,\n" +
    "                    \"thumbnail\": \"default\",\n" +
    "                    \"edited\": false,\n" +
    "                    \"link_flair_css_class\": null,\n" +
    "                    \"author_flair_css_class\": null,\n" +
    "                    \"contest_mode\": false,\n" +
    "                    \"gilded\": 0,\n" +
    "                    \"downs\": 0,\n" +
    "                    \"brand_safe\": true,\n" +
    "                    \"secure_media_embed\": {},\n" +
    "                    \"media_embed\": {},\n" +
    "                    \"author_flair_text\": null,\n" +
    "                    \"stickied\": false,\n" +
    "                    \"visited\": false,\n" +
    "                    \"can_gild\": false,\n" +
    "                    \"thumbnail_height\": null,\n" +
    "                    \"parent_whitelist_status\": \"all_ads\",\n" +
    "                    \"name\": \"t3_85706t\",\n" +
    "                    \"spoiler\": false,\n" +
    "                    \"permalink\": \"/r/Android/comments/85706t/a_while_back_i_sought_a_solution_to_hide_the/\",\n" +
    "                    \"subreddit_type\": \"public\",\n" +
    "                    \"locked\": false,\n" +
    "                    \"hide_score\": true,\n" +
    "                    \"created\": 1521352678,\n" +
    "                    \"url\": \"https://play.google.com/store/apps/details?id=com.gmd.immersive\",\n" +
    "                    \"whitelist_status\": \"all_ads\",\n" +
    "                    \"quarantine\": false,\n" +
    "                    \"subreddit_subscribers\": 1091624,\n" +
    "                    \"created_utc\": 1521323878,\n" +
    "                    \"subreddit_name_prefixed\": \"r/Android\",\n" +
    "                    \"ups\": 1,\n" +
    "                    \"media\": null,\n" +
    "                    \"num_comments\": 0,\n" +
    "                    \"is_self\": false,\n" +
    "                    \"title\": \"A while back I sought a solution to hide the icon/status bar at the top of the gui and this app takes care of that to the utmost adroit fashion above any other I tested.\",\n" +
    "                    \"mod_note\": null,\n" +
    "                    \"is_video\": false,\n" +
    "                    \"distinguished\": null\n" +
    "                }\n" +
    "            }\n" +
    "        ],\n" +
    "        \"before\": null\n" +
    "    }\n" +
    "}"