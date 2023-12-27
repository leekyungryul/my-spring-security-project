-- 사용자 정보 테이블
create table public.my_user
(
    user_id         varchar(36)  not null
        constraint my_user_pk
            primary key,
    login_id        varchar(100) not null,
    user_name       varchar(50),
    user_pwd        varchar(64)  not null,
    status          char         not null,
    email           varchar(100),
    tel_no          varchar(32),
    timezone_id     varchar(10),
    date_format     varchar(10),
    time_format     varchar(8),
    language        varchar(2),
    gender          char,
    birth           varchar(10),
    zip_code        varchar(10),
    address         varchar(100),
    address_detail  varchar(100),
    join_date       varchar(10),
    national_info   varchar(2),
    pwd_fail_count  integer,
    pwd_change_dttm timestamp,
    expire_dttm     timestamp,
    creator_id      varchar(36),
    create_dttm     timestamp,
    updater_id      varchar(36),
    update_dttm     timestamp
);

comment on table public.my_user is '사용자';
comment on column public.my_user.user_id is '사용자 아이디';
comment on column public.my_user.login_id is '로그인 아이디';
comment on column public.my_user.user_name is '사용자 이름';
comment on column public.my_user.user_pwd is '사용자 비밀번호';
comment on column public.my_user.status is '사용자 상태';
comment on column public.my_user.email is '이메일';
comment on column public.my_user.tel_no is '전화번호';
comment on column public.my_user.timezone_id is '시간대';
comment on column public.my_user.date_format is '날짜 형식';
comment on column public.my_user.time_format is '시간 형식';
comment on column public.my_user.language is '언어';
comment on column public.my_user.gender is '성별';
comment on column public.my_user.birth is '생년월일';
comment on column public.my_user.zip_code is '우편번호';
comment on column public.my_user.address is '주소';
comment on column public.my_user.address_detail is '상세주소';
comment on column public.my_user.join_date is '가입일';
comment on column public.my_user.national_info is '국적';
comment on column public.my_user.pwd_fail_count is '비밀번호 실패 횟수';
comment on column public.my_user.pwd_change_dttm is '비밀번호 변경 일시';
comment on column public.my_user.expire_dttm is '만료 일시';
comment on column public.my_user.creator_id is '생성자 아이디';
comment on column public.my_user.create_dttm is '생성 일시';
comment on column public.my_user.updater_id is '수정자 아이디';
comment on column public.my_user.update_dttm is '수정 일시';

alter table public.my_user
    owner to my_user;

-- 사용자 권한 테이블
create table public.my_role
(
    role_id     varchar(36)  not null
        constraint my_role_pk
            primary key,
    role_code   varchar(20)  not null,
    role_name   varchar(100) not null,
    description varchar(200),
    creator_id  char(36),
    create_dttm timestamp,
    updater_id  char(36),
    update_dttm timestamp
);

alter table public.my_role
    owner to my_user;

comment on table public.my_role is '역할';
comment on column public.my_role.role_id is '역할 아이디';
comment on column public.my_role.role_code is '역할 코드';
comment on column public.my_role.role_name is '역할 이름';
comment on column public.my_role.description is '설명';
comment on column public.my_role.creator_id is '생성자 아이디';
comment on column public.my_role.create_dttm is '생성 일시';
comment on column public.my_role.updater_id is '수정자 아이디';
comment on column public.my_role.update_dttm is '수정 일시';

-- 사용자 권한 매핑 테이블
create table public.my_user_role
(
    role_id     varchar(36) not null,
    user_id     varchar(36) not null,
    creator_id  char(36),
    create_dttm timestamp,
    updater_id  char(36),
    update_dttm timestamp,
    constraint my_user_role_pk
        primary key (role_id, user_id)
);

comment on column public.my_user_role.role_id is '역할 아이디';

comment on column public.my_user_role.user_id is '사용자 아이디';

comment on column public.my_user_role.creator_id is '생성자 아이디';

comment on column public.my_user_role.create_dttm is '생성일시';

comment on column public.my_user_role.updater_id is '수정자 아이디';

comment on column public.my_user_role.update_dttm is '수정일시';

alter table public.my_user_role
    owner to my_user;

-- 사용자 oAuth2 정보 테이블
create table public.my_user_oauth
(
    user_id          varchar(36) not null,
    provider         varchar(20) not null,
    provider_user_id varchar(50) not null,
    creator_id       varchar(36),
    create_dttm       timestamp,
    updater_id       varchar(36),
    update_dttm       timestamp
);

comment on table public.my_user_oauth is 'oauth user info';

comment on column public.my_user_oauth.user_id is 'user id';

comment on column public.my_user_oauth.provider is 'oauth provider';

comment on column public.my_user_oauth.provider_user_id is 'oauth provider user id';

comment on column public.my_user_oauth.creator_id is 'creator id';

comment on column public.my_user_oauth.create_dttm is 'create date time';

comment on column public.my_user_oauth.updater_id is 'updater id';

comment on column public.my_user_oauth.update_dttm is 'update date time';

alter table public.my_user_oauth
    owner to my_user;



SELECT * FROM pg_extension;
SELECT * FROM pg_available_extensions;
CREATE EXTENSION "uuid-ossp";
