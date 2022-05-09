package ch03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class userDto {
	@NonNull
	private String userName;
	private int birthYesr;
	private String addr;
	private String moblie;
	private String prodName;
	private int price;
	private int amount;
}
