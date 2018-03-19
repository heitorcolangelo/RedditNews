package com.heitorcolangelo.redditnews.mock

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.heitorcolangelo.repository.model.Comment
import com.heitorcolangelo.repository.model.Content
import com.heitorcolangelo.repository.model.Page
import com.heitorcolangelo.repository.model.ResponseData

fun commentsEmptyResponse(): List<ResponseData<Comment>> {
    val type = object : TypeToken<List<ResponseData<Comment>>>() {}.type
    return Gson().fromJson<List<ResponseData<Comment>>>(emptyCommentsMock, type)
}

fun commentsResponse(): List<ResponseData<Comment>> {
    val type = object : TypeToken<List<ResponseData<Comment>>>() {}.type
    return Gson().fromJson<List<ResponseData<Comment>>>(commentMock, type)
}

fun contentEmptyResponse() = ResponseData<Content>(Page("", listOf()))

fun contentResponse(selfText: Boolean = true): ResponseData<Content> {
    val type = object : TypeToken<ResponseData<Content>>() {}.type
    return Gson().fromJson<ResponseData<Content>>(if (selfText) contentMock else noSelfTextContentMock, type)
}

private const val emptyCommentsMock = "[\n" +
    "  {\n" +
    "    \"kind\": \"Listing\",\n" +
    "    \"data\": {\n" +
    "      \"after\": null,\n" +
    "      \"whitelist_status\": \"all_ads\",\n" +
    "      \"modhash\": \"\",\n" +
    "      \"dist\": 1,\n" +
    "      \"children\": [\n" +
    "        {\n" +
    "          \"kind\": \"t3\",\n" +
    "          \"data\": {\n" +
    "            \"is_crosspostable\": false,\n" +
    "            \"subreddit_id\": \"t5_2qlqh\",\n" +
    "            \"approved_at_utc\": null,\n" +
    "            \"mod_reason_by\": null,\n" +
    "            \"banned_by\": null,\n" +
    "            \"removal_reason\": null,\n" +
    "            \"thumbnail_width\": 140,\n" +
    "            \"subreddit\": \"Android\",\n" +
    "            \"selftext_html\": null,\n" +
    "            \"selftext\": \"\",\n" +
    "            \"likes\": null,\n" +
    "            \"suggested_sort\": null,\n" +
    "            \"mod_note\": null,\n" +
    "            \"user_reports\": [],\n" +
    "            \"secure_media\": null,\n" +
    "            \"is_reddit_media_domain\": false,\n" +
    "            \"saved\": false,\n" +
    "            \"id\": \"85m17q\",\n" +
    "            \"banned_at_utc\": null,\n" +
    "            \"mod_reason_title\": null,\n" +
    "            \"view_count\": null,\n" +
    "            \"archived\": false,\n" +
    "            \"clicked\": false,\n" +
    "            \"no_follow\": true,\n" +
    "            \"author\": \"najodleglejszy\",\n" +
    "            \"num_crossposts\": 0,\n" +
    "            \"link_flair_text\": null,\n" +
    "            \"can_mod_post\": false,\n" +
    "            \"send_replies\": true,\n" +
    "            \"pinned\": false,\n" +
    "            \"score\": 0,\n" +
    "            \"approved_by\": null,\n" +
    "            \"over_18\": false,\n" +
    "            \"domain\": \"adwords.googleblog.com\",\n" +
    "            \"hidden\": false,\n" +
    "            \"preview\": {\n" +
    "              \"images\": [\n" +
    "                {\n" +
    "                  \"source\": {\n" +
    "                    \"url\": \"https://i.redditmedia.com/PNhzTGGvVAyCBt341idFD26HTTGAQff98Vs9EAoHfbA.jpg?fm=jpg&amp;s=28cd9cde0936a633901be6c0f0bfbfd4\",\n" +
    "                    \"width\": 407,\n" +
    "                    \"height\": 214\n" +
    "                  },\n" +
    "                  \"resolutions\": [\n" +
    "                    {\n" +
    "                      \"url\": \"https://i.redditmedia.com/PNhzTGGvVAyCBt341idFD26HTTGAQff98Vs9EAoHfbA.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=108&amp;fm=jpg&amp;s=aaabf9ff934939095b1ec9aeb566bff4\",\n" +
    "                      \"width\": 108,\n" +
    "                      \"height\": 56\n" +
    "                    },\n" +
    "                    {\n" +
    "                      \"url\": \"https://i.redditmedia.com/PNhzTGGvVAyCBt341idFD26HTTGAQff98Vs9EAoHfbA.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=216&amp;fm=jpg&amp;s=c0dad2b353fe85b6cc4680581508c3bd\",\n" +
    "                      \"width\": 216,\n" +
    "                      \"height\": 113\n" +
    "                    },\n" +
    "                    {\n" +
    "                      \"url\": \"https://i.redditmedia.com/PNhzTGGvVAyCBt341idFD26HTTGAQff98Vs9EAoHfbA.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=320&amp;fm=jpg&amp;s=9c12d268b488d63075f0d4220cebb337\",\n" +
    "                      \"width\": 320,\n" +
    "                      \"height\": 168\n" +
    "                    }\n" +
    "                  ],\n" +
    "                  \"variants\": {},\n" +
    "                  \"id\": \"UdFmG5yCWYl1aJh64Wqj60dPdSOIyhGjY9VOnnvthUo\"\n" +
    "                }\n" +
    "              ],\n" +
    "              \"enabled\": false\n" +
    "            },\n" +
    "            \"num_comments\": 1,\n" +
    "            \"thumbnail\": \"https://b.thumbs.redditmedia.com/oJyKxFbsOeqd43Uw1XRVYRu9OYZsQLJeja4ECe6rrwA.jpg\",\n" +
    "            \"hide_score\": false,\n" +
    "            \"edited\": false,\n" +
    "            \"link_flair_css_class\": null,\n" +
    "            \"author_flair_css_class\": \"userOrange\",\n" +
    "            \"contest_mode\": false,\n" +
    "            \"gilded\": 0,\n" +
    "            \"locked\": false,\n" +
    "            \"downs\": 0,\n" +
    "            \"brand_safe\": true,\n" +
    "            \"subreddit_subscribers\": 1093269,\n" +
    "            \"secure_media_embed\": {},\n" +
    "            \"media_embed\": {},\n" +
    "            \"post_hint\": \"link\",\n" +
    "            \"stickied\": false,\n" +
    "            \"can_gild\": false,\n" +
    "            \"thumbnail_height\": 73,\n" +
    "            \"parent_whitelist_status\": \"all_ads\",\n" +
    "            \"name\": \"t3_85m17q\",\n" +
    "            \"spoiler\": false,\n" +
    "            \"permalink\": \"/r/Android/comments/85m17q/become_the_goat_of_mobile_gaming_with_new/\",\n" +
    "            \"subreddit_type\": \"public\",\n" +
    "            \"whitelist_status\": \"all_ads\",\n" +
    "            \"report_reasons\": null,\n" +
    "            \"created\": 1521514799.0,\n" +
    "            \"url\": \"https://adwords.googleblog.com/2018/03/become-goat-of-mobile-gaming-with-new.html?m=1\",\n" +
    "            \"author_flair_text\": \"XZ Premium 8.1 when\",\n" +
    "            \"quarantine\": false,\n" +
    "            \"title\": \"Become the GOAT of mobile gaming with new innovations from AdWords &amp; AdMob\",\n" +
    "            \"created_utc\": 1521485999.0,\n" +
    "            \"subreddit_name_prefixed\": \"r/Android\",\n" +
    "            \"distinguished\": null,\n" +
    "            \"media\": null,\n" +
    "            \"upvote_ratio\": 0.38,\n" +
    "            \"mod_reports\": [],\n" +
    "            \"is_self\": false,\n" +
    "            \"visited\": false,\n" +
    "            \"num_reports\": null,\n" +
    "            \"is_video\": false,\n" +
    "            \"ups\": 0\n" +
    "          }\n" +
    "        }\n" +
    "      ],\n" +
    "      \"before\": null\n" +
    "    }\n" +
    "  },\n" +
    "  {\n" +
    "    \"kind\": \"Listing\",\n" +
    "    \"data\": {\n" +
    "      \"after\": null,\n" +
    "      \"whitelist_status\": \"all_ads\",\n" +
    "      \"modhash\": \"\",\n" +
    "      \"dist\": null,\n" +
    "      \"children\": [],\n" +
    "      \"before\": null\n" +
    "    }\n" +
    "  }\n" +
    "]"

private const val commentMock = "[\n" +
    "  {\n" +
    "    \"kind\": \"Listing\",\n" +
    "    \"data\": {\n" +
    "      \"after\": null,\n" +
    "      \"whitelist_status\": \"all_ads\",\n" +
    "      \"modhash\": \"\",\n" +
    "      \"dist\": 1,\n" +
    "      \"children\": [\n" +
    "        {\n" +
    "          \"kind\": \"t3\",\n" +
    "          \"data\": {\n" +
    "            \"is_crosspostable\": false,\n" +
    "            \"subreddit_id\": \"t5_2qlqh\",\n" +
    "            \"approved_at_utc\": null,\n" +
    "            \"mod_reason_by\": null,\n" +
    "            \"banned_by\": null,\n" +
    "            \"removal_reason\": null,\n" +
    "            \"thumbnail_width\": 140,\n" +
    "            \"subreddit\": \"Android\",\n" +
    "            \"selftext_html\": null,\n" +
    "            \"selftext\": \"\",\n" +
    "            \"likes\": null,\n" +
    "            \"suggested_sort\": null,\n" +
    "            \"mod_note\": null,\n" +
    "            \"user_reports\": [],\n" +
    "            \"secure_media\": null,\n" +
    "            \"is_reddit_media_domain\": false,\n" +
    "            \"saved\": false,\n" +
    "            \"id\": \"85m17q\",\n" +
    "            \"banned_at_utc\": null,\n" +
    "            \"mod_reason_title\": null,\n" +
    "            \"view_count\": null,\n" +
    "            \"archived\": false,\n" +
    "            \"clicked\": false,\n" +
    "            \"no_follow\": true,\n" +
    "            \"author\": \"najodleglejszy\",\n" +
    "            \"num_crossposts\": 0,\n" +
    "            \"link_flair_text\": null,\n" +
    "            \"can_mod_post\": false,\n" +
    "            \"send_replies\": true,\n" +
    "            \"pinned\": false,\n" +
    "            \"score\": 0,\n" +
    "            \"approved_by\": null,\n" +
    "            \"over_18\": false,\n" +
    "            \"domain\": \"adwords.googleblog.com\",\n" +
    "            \"hidden\": false,\n" +
    "            \"preview\": {\n" +
    "              \"images\": [\n" +
    "                {\n" +
    "                  \"source\": {\n" +
    "                    \"url\": \"https://i.redditmedia.com/PNhzTGGvVAyCBt341idFD26HTTGAQff98Vs9EAoHfbA.jpg?fm=jpg&amp;s=28cd9cde0936a633901be6c0f0bfbfd4\",\n" +
    "                    \"width\": 407,\n" +
    "                    \"height\": 214\n" +
    "                  },\n" +
    "                  \"resolutions\": [\n" +
    "                    {\n" +
    "                      \"url\": \"https://i.redditmedia.com/PNhzTGGvVAyCBt341idFD26HTTGAQff98Vs9EAoHfbA.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=108&amp;fm=jpg&amp;s=aaabf9ff934939095b1ec9aeb566bff4\",\n" +
    "                      \"width\": 108,\n" +
    "                      \"height\": 56\n" +
    "                    },\n" +
    "                    {\n" +
    "                      \"url\": \"https://i.redditmedia.com/PNhzTGGvVAyCBt341idFD26HTTGAQff98Vs9EAoHfbA.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=216&amp;fm=jpg&amp;s=c0dad2b353fe85b6cc4680581508c3bd\",\n" +
    "                      \"width\": 216,\n" +
    "                      \"height\": 113\n" +
    "                    },\n" +
    "                    {\n" +
    "                      \"url\": \"https://i.redditmedia.com/PNhzTGGvVAyCBt341idFD26HTTGAQff98Vs9EAoHfbA.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=320&amp;fm=jpg&amp;s=9c12d268b488d63075f0d4220cebb337\",\n" +
    "                      \"width\": 320,\n" +
    "                      \"height\": 168\n" +
    "                    }\n" +
    "                  ],\n" +
    "                  \"variants\": {},\n" +
    "                  \"id\": \"UdFmG5yCWYl1aJh64Wqj60dPdSOIyhGjY9VOnnvthUo\"\n" +
    "                }\n" +
    "              ],\n" +
    "              \"enabled\": false\n" +
    "            },\n" +
    "            \"num_comments\": 1,\n" +
    "            \"thumbnail\": \"https://b.thumbs.redditmedia.com/oJyKxFbsOeqd43Uw1XRVYRu9OYZsQLJeja4ECe6rrwA.jpg\",\n" +
    "            \"hide_score\": false,\n" +
    "            \"edited\": false,\n" +
    "            \"link_flair_css_class\": null,\n" +
    "            \"author_flair_css_class\": \"userOrange\",\n" +
    "            \"contest_mode\": false,\n" +
    "            \"gilded\": 0,\n" +
    "            \"locked\": false,\n" +
    "            \"downs\": 0,\n" +
    "            \"brand_safe\": true,\n" +
    "            \"subreddit_subscribers\": 1093269,\n" +
    "            \"secure_media_embed\": {},\n" +
    "            \"media_embed\": {},\n" +
    "            \"post_hint\": \"link\",\n" +
    "            \"stickied\": false,\n" +
    "            \"can_gild\": false,\n" +
    "            \"thumbnail_height\": 73,\n" +
    "            \"parent_whitelist_status\": \"all_ads\",\n" +
    "            \"name\": \"t3_85m17q\",\n" +
    "            \"spoiler\": false,\n" +
    "            \"permalink\": \"/r/Android/comments/85m17q/become_the_goat_of_mobile_gaming_with_new/\",\n" +
    "            \"subreddit_type\": \"public\",\n" +
    "            \"whitelist_status\": \"all_ads\",\n" +
    "            \"report_reasons\": null,\n" +
    "            \"created\": 1521514799.0,\n" +
    "            \"url\": \"https://adwords.googleblog.com/2018/03/become-goat-of-mobile-gaming-with-new.html?m=1\",\n" +
    "            \"author_flair_text\": \"XZ Premium 8.1 when\",\n" +
    "            \"quarantine\": false,\n" +
    "            \"title\": \"Become the GOAT of mobile gaming with new innovations from AdWords &amp; AdMob\",\n" +
    "            \"created_utc\": 1521485999.0,\n" +
    "            \"subreddit_name_prefixed\": \"r/Android\",\n" +
    "            \"distinguished\": null,\n" +
    "            \"media\": null,\n" +
    "            \"upvote_ratio\": 0.38,\n" +
    "            \"mod_reports\": [],\n" +
    "            \"is_self\": false,\n" +
    "            \"visited\": false,\n" +
    "            \"num_reports\": null,\n" +
    "            \"is_video\": false,\n" +
    "            \"ups\": 0\n" +
    "          }\n" +
    "        }\n" +
    "      ],\n" +
    "      \"before\": null\n" +
    "    }\n" +
    "  },\n" +
    "  {\n" +
    "    \"kind\": \"Listing\",\n" +
    "    \"data\": {\n" +
    "      \"after\": null,\n" +
    "      \"whitelist_status\": \"all_ads\",\n" +
    "      \"modhash\": \"\",\n" +
    "      \"dist\": null,\n" +
    "      \"children\": [\n" +
    "        {\n" +
    "          \"kind\": \"t1\",\n" +
    "          \"data\": {\n" +
    "            \"subreddit_id\": \"t5_2qlqh\",\n" +
    "            \"approved_at_utc\": null,\n" +
    "            \"mod_reason_by\": null,\n" +
    "            \"banned_by\": null,\n" +
    "            \"removal_reason\": null,\n" +
    "            \"link_id\": \"t3_85m17q\",\n" +
    "            \"likes\": null,\n" +
    "            \"no_follow\": true,\n" +
    "            \"replies\": \"\",\n" +
    "            \"user_reports\": [],\n" +
    "            \"saved\": false,\n" +
    "            \"id\": \"dvydh6y\",\n" +
    "            \"banned_at_utc\": null,\n" +
    "            \"mod_reason_title\": null,\n" +
    "            \"gilded\": 0,\n" +
    "            \"archived\": false,\n" +
    "            \"report_reasons\": null,\n" +
    "            \"author\": \"najodleglejszy\",\n" +
    "            \"can_mod_post\": false,\n" +
    "            \"ups\": -1,\n" +
    "            \"parent_id\": \"t3_85m17q\",\n" +
    "            \"score\": -1,\n" +
    "            \"approved_by\": null,\n" +
    "            \"downs\": 0,\n" +
    "            \"body\": \"&gt;Over the next few months, we’ll roll out a beta for **click-to-play video ads**—a new way to reach players on Google Play with sight, sound and motion. These placements will help you showcase your game as they tap, swipe and scroll to find their next favorite app.\",\n" +
    "            \"edited\": false,\n" +
    "            \"author_flair_css_class\": \"userOrange\",\n" +
    "            \"collapsed\": false,\n" +
    "            \"is_submitter\": true,\n" +
    "            \"collapsed_reason\": null,\n" +
    "            \"body_html\": \"&lt;div class=\\\"md\\\"&gt;&lt;blockquote&gt;\\n&lt;p&gt;Over the next few months, we’ll roll out a beta for &lt;strong&gt;click-to-play video ads&lt;/strong&gt;—a new way to reach players on Google Play with sight, sound and motion. These placements will help you showcase your game as they tap, swipe and scroll to find their next favorite app.&lt;/p&gt;\\n&lt;/blockquote&gt;\\n&lt;/div&gt;\",\n" +
    "            \"subreddit_type\": \"public\",\n" +
    "            \"can_gild\": true,\n" +
    "            \"subreddit\": \"Android\",\n" +
    "            \"name\": \"t1_dvydh6y\",\n" +
    "            \"score_hidden\": false,\n" +
    "            \"permalink\": \"/r/Android/comments/85m17q/become_the_goat_of_mobile_gaming_with_new/dvydh6y/\",\n" +
    "            \"num_reports\": null,\n" +
    "            \"stickied\": false,\n" +
    "            \"created\": 1521514837.0,\n" +
    "            \"author_flair_text\": \"XZ Premium 8.1 when\",\n" +
    "            \"created_utc\": 1521486037.0,\n" +
    "            \"subreddit_name_prefixed\": \"r/Android\",\n" +
    "            \"controversiality\": 0,\n" +
    "            \"depth\": 0,\n" +
    "            \"mod_reports\": [],\n" +
    "            \"mod_note\": null,\n" +
    "            \"distinguished\": null\n" +
    "          }\n" +
    "        }\n" +
    "      ],\n" +
    "      \"before\": null\n" +
    "    }\n" +
    "  }\n" +
    "]"

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
    "                    \"selftext\": \"This is a self text made up to create a valid test scenario\",\n" +
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

private const val noSelfTextContentMock = "{\n" +
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