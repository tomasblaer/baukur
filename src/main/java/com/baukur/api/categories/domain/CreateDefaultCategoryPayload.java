package com.baukur.api.categories.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDefaultCategoryPayload {
    List<Long> ids;
    Long userId;
}
