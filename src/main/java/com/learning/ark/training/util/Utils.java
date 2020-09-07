package com.learning.ark.training.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.learning.ark.training.exception.TrainingException;

public interface Utils {

	static String nullOrTrimmed(String value) {
		return StringUtils.isBlank(value) ? null : value.trim();
	}

	static void rejectIfExceedsMaxLength(String name, String value, int maxLength) {
		if (StringUtils.isNotEmpty(value) && value.trim().length() > maxLength) {
			throw new TrainingException(
					Constants.VALIDATION_ERROR + String.format(Constants.INVALID_LENGTH, name, maxLength),
					HttpStatus.BAD_REQUEST);
		}
	}
}
