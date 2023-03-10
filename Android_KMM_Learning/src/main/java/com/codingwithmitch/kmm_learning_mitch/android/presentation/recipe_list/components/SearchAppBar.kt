package com.codingwithmitch.kmm_learning_mitch.android.presentation.recipe_list.components

import androidx.compose.compiler.plugins.kotlin.ComposeFqNames
import androidx.compose.compiler.plugins.kotlin.ComposeFqNames.remember
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codingwithmitch.kmm_learning_mitch.presentation.recipe_list.FoodCategory
import com.codingwithmitch.kmm_learning_mitch.presentation.recipe_list.FoodCategoryUtil

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(
    query:String,
    categories:List<FoodCategory>,
    selectedCategory:FoodCategory?=null,
    onQueryChange:(String)->Unit,
    onSelectCategory:(FoodCategory)->Unit,
    onExcuteSearch:()->Unit,

)
{
    Surface(modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.secondary, elevation = 8.dp,)
    {
        val keyboardController=LocalSoftwareKeyboardController.current

        Column()
        {
            Row(
                modifier = Modifier.fillMaxWidth()
            )
            {
                TextField(value = query,
                    onValueChange = {onQueryChange(it) },
                    label = { Text("Search...") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onExcuteSearch()
                            keyboardController?.hide()
                        }
                    ),
                    leadingIcon = {
                        Icon(Icons.Filled.Search,
                        contentDescription = "Search For Recipe")
                    },
                    textStyle = TextStyle(color=MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor =
                        MaterialTheme.colors.surface)
                )
            }

            LazyRow(modifier = Modifier.padding(start = 8.dp, bottom = 8.dp,))
            {
                items(categories)
                {
                    FoodCategoryChip(category = it.value,
                        isSelected = selectedCategory==it,
                        onSelectedCategoryChanged = {
                            FoodCategoryUtil().getFoodCategory(it)
                                ?.let { onSelectCategory(it) }

                        })
                }
            }

        }

    }


}

