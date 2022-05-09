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
public class BuyDto {
	
	@NonNull
	private String userName;
	private String addr;
	private String prodName;
	private int price;
	
}
