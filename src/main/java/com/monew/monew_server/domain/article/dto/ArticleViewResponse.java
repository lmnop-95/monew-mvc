package com.monew.monew_server.domain.article.dto;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleViewResponse {

	private UUID id;
	private UUID viewedBy;
	private Instant createdAt;
	private UUID articleId;
	private String source;
	private String sourceUrl;
	private String articleTitle;
	private Instant articlePublishedDate;
	private String articleSummary;
	private long articleCommentCount;
	private long articleViewCount;
}
