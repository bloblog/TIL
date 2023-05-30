SELECT *
FROM webmarket.purchase_log_with_coupon;

SELECT purchase_id
	, amount
    , coupon
    , amount-COALESCE(coupon, 0) AS discount_amount -- 널값이면 0으로 처리
FROM webmarket.purchase_log_with_coupon;