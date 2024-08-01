INSERT INTO tb_accounts (id, food_amount, meal_amount, cash_amount, created_at, updated_at)
VALUES ('e7b8a9d2-4c3b-4f8e-9b8e-1a2b3c4d5e6f', 100.00, 200.00, 3000.00, NOW(), NOW()),
       ('f8c9d0e1-5a6b-7c8d-9e0f-1a2b3c4d5e6f', 150.00, 250.00, 3500.00, NOW(), NOW());

INSERT INTO tb_transactions (id, account_id, status, total_amount, created_at, updated_at, mcc, merchant) VALUES
                                                                                                                           ('a1b2c3d4-5e6f-7a8b-9c0d-1e2f3a4b5c6d', 'e7b8a9d2-4c3b-4f8e-9b8e-1a2b3c4d5e6f', 'PENDING_AUTHORIZATION', 500.00, NOW(), NOW(), '5411', 'UBER TRIP'),
                                                                                                                           ('b2c3d4e5-6f7a-8b9c-0d1e-2f3a4b5c6d7e', 'e7b8a9d2-4c3b-4f8e-9b8e-1a2b3c4d5e6f', 'PENDING_AUTHORIZATION', 75.00, NOW(), NOW(), '5412', 'UBER EATS'),
                                                                                                                           ('c3d4e5f6-7a8b-9c0d-1e2f-3a4b5c6d7e8f', 'e7b8a9d2-4c3b-4f8e-9b8e-1a2b3c4d5e6f', 'PENDING_AUTHORIZATION', 500.00, NOW(), NOW(), '7011', 'UBER TRIP 2'),
                                                                                                                           ('d4e5f6a7-8b9c-0d1e-2f3a-4b5c6d7e8f9f', 'e7b8a9d2-4c3b-4f8e-9b8e-1a2b3c4d5e6f', 'PENDING_AUTHORIZATION', 150.00, NOW(), NOW(), '5812', 'Merchant D'),
                                                                                                                           ('e5f6a7b8-9c0d-1e2f-3a4b-5c6d7e8f9a0b', 'e7b8a9d2-4c3b-4f8e-9b8e-1a2b3c4d5e6f', 'PENDING_AUTHORIZATION', 250.00, NOW(), NOW(), '5411', 'Merchant E');