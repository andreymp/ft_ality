NAME=ft_ality
PATH=src/main/scala/edu/school42/ftality/
UTILITY=utility/utility.scala
PARSING=parsing/parsing.scala
TRAINING=training/training.scala
SCALAC=scalac

all: $(NAME)

$(NAME):
	@$(SCALAC) $(PATH)$(UTILITY) \
			$(PATH)$(PARSING) \
			$(PATH)$(TRAINING) \
			$(PATH)Main.scala